package org.jesperancinha.sftd.flash31.bean.initialization;

import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash31.bean.initialization.domain.BookService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class SpringFlash31Launcher implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash31Launcher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Consolerizer.setupFastDefault();
        Consolerizer.titleSpread = 200;
        Consolerizer.maxLineCharsGlobal = 200;
        classPathXmlApplicationContext();
        fileSystemXmlApplicationContext();
    }

    private static void fileSystemXmlApplicationContext() {
        ConsolerizerComposer
                .outSpace()
                .brightGreen(title("Starting FileSystemXmlApplicationContext test"))
                .reset();
        final var path = SpringFlash31Launcher.class.getResource("/beans.xml").toExternalForm();
        ConfigurableApplicationContext fileSystemXmlApplicationContext =
                new FileSystemXmlApplicationContext(path);
        final var bookService = (BookService) fileSystemXmlApplicationContext.getBean("bookService");
        final var bookService2 = (BookService) fileSystemXmlApplicationContext.getBean("bookService2");
        final var bookService3 = (BookService) fileSystemXmlApplicationContext.getBean("bookService3");
        CYAN.printGenericTitleLn("This is the %s instance", bookService.getClass());
        BLUE.printGenericLn("The instance has these contents %s", bookService);
        CYAN.printGenericTitleLn("This is the %s instance", bookService2.getClass());
        BLUE.printGenericLn("The instance 2 has these contents %s", bookService2);
        CYAN.printGenericTitleLn("This is the %s instance", bookService3.getClass());
        BLUE.printGenericLn("The instance 3 has these contents %s", bookService3);
    }

    private static ConfigurableApplicationContext classPathXmlApplicationContext() {
        ConsolerizerComposer
                .outSpace()
                .brightGreen(title("Starting ConfigurableApplicationContext test"))
                .reset();
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        final var bookService = (BookService) context.getBean("bookService");
        final var bookService2 = (BookService) context.getBean("bookService2");
        final var bookService3 = (BookService) context.getBean("bookService3");
        CYAN.printGenericTitleLn("This is the %s instance", bookService.getClass());
        BLUE.printGenericLn("The instance has these contents %s", bookService);
        CYAN.printGenericTitleLn("This is the %s instance", bookService2.getClass());
        BLUE.printGenericLn("The instance 2 has these contents %s", bookService2);
        CYAN.printGenericTitleLn("This is the %s instance", bookService3.getClass());
        BLUE.printGenericLn("The instance 3 has these contents %s", bookService3);
        context.close();
        return context;
    }
}
