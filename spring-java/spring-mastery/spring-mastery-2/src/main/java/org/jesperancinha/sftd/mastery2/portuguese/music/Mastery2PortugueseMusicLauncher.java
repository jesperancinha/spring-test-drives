package org.jesperancinha.sftd.mastery2.portuguese.music;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.mastery2.portuguese.music.model.Publisher;
import org.jesperancinha.sftd.mastery2.portuguese.music.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterChainProxy;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Mastery2PortugueseMusicLauncher implements CommandLineRunner {

    private final PublisherRepository publisherRepository;

    private final ApplicationContext applicationContext;

    @Value("${management.server.port}")
    private Long actuatorPort;

    @Value("${server.port}")
    private Long serverPort;

    public Mastery2PortugueseMusicLauncher(PublisherRepository publisherRepository, ApplicationContext applicationContext) {
        this.publisherRepository = publisherRepository;
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{Mastery2PortugueseMusicLauncher.class}, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var publisher1 = new Publisher();
        publisher1.setName("EMI");
        publisher1.setFoundationDate(LocalDate.of(1931, 3, 1));
        publisher1.setCity("London");

        final Publisher save = publisherRepository.save(publisher1);

        ConsolerizerComposer.out(" ")
                .brightRed("If you noticed we didn't configured any database!")
                .red("We just persisted this:")
                .newLine()
                .orange(save)
                .green("To check this, we will do a find all now.")
                .toConsoleLn();

        final List<Publisher> all = publisherRepository.findAll();

        ConsolerizerComposer.out(" ")
                .yellow("As you can see")
                .orange(all)
                .yellow("is in the database!")
                .toConsoleLn();

        ConsolerizerComposer.out(" ")
                .cyan("We can now analyze our ports")
                .newLine()
                .magenta("The actuator port can be updated to")
                .yellow(actuatorPort)
                .newLine()
                .magenta("The server port can be updated to")
                .orange(serverPort)
                .newLine()
                .magenta("The server port can be shared with the actuator.")
                .toConsoleLn();

        FilterChainProxy springSecurityFilterChain = applicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);

        ConsolerizerComposer.out(" ")
                .green(springSecurityFilterChain)
                .orange(springSecurityFilterChain.getFilterChains())
                .toConsoleLn();
    }
}