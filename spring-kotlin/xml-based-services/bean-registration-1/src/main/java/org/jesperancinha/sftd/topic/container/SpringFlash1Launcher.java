package org.jesperancinha.sftd.topic.container;

import org.jesperancinha.sftd.topic.container.converters.LimeBasket;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

public class  SpringFlash1Launcher {
    public static void main(String[] args) {
        final GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();

        genericXmlApplicationContext.load("classpath:beans.xml");
        genericXmlApplicationContext.refresh();

        final LimeBasket limeBasket = (LimeBasket) genericXmlApplicationContext.getBean("limeBasket");

        GREEN.printGenericTitleLn("We can create converters by using Registrars");
        BRIGHT_BLUE.printGenericLn(limeBasket);
    }
}
