package org.jesperancinha.sftd.flash29.security;

import jakarta.transaction.Transactional;
import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import static org.jesperancinha.sftd.flash29.security.services.JewelType.AMETHYST;


/**
 * prePost is activated by default
 */
@SpringBootApplication
@EnableMethodSecurity
public class SpringFlash29Launcher implements CommandLineRunner {

    private final JewelRepository jewelRepository;

    public SpringFlash29Launcher(JewelRepository jewelRepository) {
        this.jewelRepository = jewelRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash29Launcher.class, args).start();
    }

    @Override
    public void run(String... args) {
        Consolerizer.printRainbowLn("Application started!");
    }

    @EventListener
    @Transactional
    public void handleContextRefreshEvent(ContextStartedEvent contextStartedEvent) {
        final Jewel jewel = Jewel.builder()
                .id(1L)
                .jewelType(AMETHYST)
                .guardian("admin")
                .build();
        Consolerizer.printRainbowLn("Inserted Data! %s".formatted(jewelRepository.save(jewel)));
    }
}
