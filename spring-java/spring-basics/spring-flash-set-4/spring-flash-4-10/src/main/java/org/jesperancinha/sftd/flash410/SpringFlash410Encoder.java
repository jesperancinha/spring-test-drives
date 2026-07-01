package org.jesperancinha.sftd.flash410;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Profile("encode")
public class SpringFlash410Encoder implements CommandLineRunner {

    @Value("${flash410.password}")
    private String password;


    public static void main(String[] args) {
        SpringApplication.run(SpringFlash410Encoder.class, args);
    }

    @Override
    public void run(String... args) {
        final var passwordEncoder = new BCryptPasswordEncoder();
        final var hashedPassword = passwordEncoder.encode(password);

        ConsolerizerComposer.outSpace()
                .blue("The encoded password for: %s", password)
                .blue("is")
                .blue(hashedPassword)
                .reset();
    }
}
