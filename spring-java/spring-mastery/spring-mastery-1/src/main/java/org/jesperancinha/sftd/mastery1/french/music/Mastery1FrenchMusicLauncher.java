package org.jesperancinha.sftd.mastery1.french.music;

import org.jesperancinha.sftd.mastery1.french.music.configuration.Mastery1Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Mastery1FrenchMusicLauncher implements CommandLineRunner {

    @Qualifier("mastery1Configuration")
    private final Mastery1Configuration mastery1Configuration;

    public Mastery1FrenchMusicLauncher(Mastery1Configuration mastery1Configuration) {
        this.mastery1Configuration = mastery1Configuration;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{Mastery1FrenchMusicLauncher.class}, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        mastery1Configuration.makeAllTest();
    }

}