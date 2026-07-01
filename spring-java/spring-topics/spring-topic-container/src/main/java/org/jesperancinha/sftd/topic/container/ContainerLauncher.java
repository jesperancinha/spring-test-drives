package org.jesperancinha.sftd.topic.container;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs;
import org.jesperancinha.sftd.topic.container.beans.Bean;
import org.jesperancinha.sftd.topic.container.beans.BeanOnlyRead;
import org.jesperancinha.sftd.topic.container.beans.BeanWithContructor;
import org.jesperancinha.sftd.topic.container.beans.Flower;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;

public class ContainerLauncher {

    private static String STATIC_KIDNEY_BEAN = "{\n" +
            "  \"name\" : \"Kidney Bean\",\n" +
            "  \"mainColor\" : \"red\",\n" +
            "  \"scientificName\" : \"Phaseolus vulgaris\"\n" +
            "}";

    public static void main(String[] args) throws JsonProcessingException {
        BRIGHT_MAGENTA.printGenericTitleLn("Runtime Started");

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("./beans.xml");
        Flower flower1 = (Flower) beanFactory.getBean("flower1");
        GREEN.printGenericTitleLn("Flower No. 1 - From BeanFactory");
        BLUE.printGenericLn("Flower Type: %s", flower1.getName());
        BLUE.printGenericLn("Flower Height: %d", flower1.getHeight());

        ApplicationContext context = new ClassPathXmlApplicationContext("./beans.xml");
        Flower flower2 = (Flower) context.getBean("flower2");

        GREEN.printGenericTitleLn("Flower No. 2 - From ApplicationContext");
        BLUE.printGenericLn("Flower Type: %s", flower2.getName());
        BLUE.printGenericLn("Flower Height: %d", flower2.getHeight());

        MAGENTA.printGenericLn("ApplicationContext is also a Bean Factory -> %s", context instanceof BeanFactory);
        MAGENTA.printGenericLn("Bean Factory is also an ApplicationContext -> %s", beanFactory instanceof ApplicationContext);
        MAGENTA.printGenericLn("Instance context is a %s", context.getClass().getCanonicalName());
        MAGENTA.printGenericLn("Instance beanFactory is a %s", beanFactory.getClass().getCanonicalName());
        ORANGE.printGenericLn("This is because both are just interfaces for an instance of the Application context");

        ConsolerizerGraphs.printUnicornsLn(100);
        GREEN.printGenericLn("Now that we have seen how beans  work with params, let's now see how they work with constructors");

        final Bean kidneyBean = (Bean) context.getBean("kidneyBean");
        final Bean scarletBean = (Bean) context.getBean("scarletBean");
        final Bean blackBean = (Bean) context.getBean("blackBean");

        MAGENTA.printGenericLn("This is the kidneyBean value %s:", kidneyBean);
        MAGENTA.printGenericLn("This is the scarletBean value %s:", scarletBean);
        MAGENTA.printGenericLn("This is the blackBean value %s:", blackBean);
        BRIGHT_MAGENTA.printGenericLn("(Note that no getters and setters are needed for this sort of instantiaion)");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(kidneyBean);
        } catch (Exception e) {
            ORANGE.printGenericLn("This is expected! Our Bean type is not ready to be serialized. The type doesn't have its get accessors-> %s", e.getMessage());
        }
        ConsolerizerGraphs.printUnicornsLn(100);
        try {
            final Bean kidneyBeanReadBack = objectMapper
                    .readValue(STATIC_KIDNEY_BEAN, Bean.class);
        } catch (Exception e) {
            ORANGE.printGenericLn("This is expected! Our Bean type is not ready to be read. The constructor doesn't have its accessors-> %s", e.getMessage());
        }

        final BeanOnlyRead blackBeanRead = (BeanOnlyRead) context.getBean("blackBeanRead");
        final String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blackBeanRead);
        MAGENTA.printGenericLn("Reading bean with just the getters, we can serialize it in a JSON form ->\n%s", jsonString);

        final BeanWithContructor kidneyBeanReadBackRight = objectMapper
                .readValue(STATIC_KIDNEY_BEAN, BeanWithContructor.class);
        GREEN.printGenericLn("Starting from JSON\n%s", STATIC_KIDNEY_BEAN);
        GREEN.printGenericLn("We just read this bean from JSON -> %s", kidneyBeanReadBackRight);

        ConsolerizerGraphs.printUnicornsLn(100);
    }
}
