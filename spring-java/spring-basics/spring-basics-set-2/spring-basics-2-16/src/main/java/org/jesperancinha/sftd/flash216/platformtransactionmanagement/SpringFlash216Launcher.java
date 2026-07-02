package org.jesperancinha.sftd.flash216.platformtransactionmanagement;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash216.platformtransactionmanagement.domain.Award;
import org.jesperancinha.sftd.flash216.platformtransactionmanagement.service.AwardDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringFlash216Launcher implements CommandLineRunner {

    private final AwardDao awardDao;

    public SpringFlash216Launcher(AwardDao awardDao) {
        this.awardDao = awardDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash216Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        awardDao.createTables();
        final Award award1 = awardDao.create("Mylène Farmer", "World's Best Selling French Artist", LocalDateTime.of(1993, 5, 31, 12, 0, 0));
        awardDao.resetDatabase();
        final Award award2 = awardDao.createRollback("Mylène Farmer", "World's Best Selling French Artist", LocalDateTime.of(1993, 5, 31, 12, 0, 0));
        awardDao.resetDatabase();
        final Award award3 = awardDao.createFailRollback("Mylène Farmer", "World's Best Selling French Artist", LocalDateTime.of(1993, 5, 31, 12, 0, 0));
        awardDao.resetDatabase();
        final Award award4 = awardDao.createNoTransaction("Mylène Farmer", "World's Best Selling French Artist", LocalDateTime.of(1993, 5, 31, 12, 0, 0));
        ConsolerizerComposer.outSpace()
                .green("Awards")
                .green("AW1 - Normal")
                .red().jsonPrettyPrint(award1)
                .green("AW2 - Rollback")
                .red().jsonPrettyPrint(award2)
                .green("AW3 - Rollback Fail")
                .red().jsonPrettyPrint(award3)
                .green("AW4 - No Commit / No Rollback")
                .red().jsonPrettyPrint(award4)
                .green("AWN - Final List!")
                .jsonPrettyPrint(awardDao.listAwards())
                .reset();
    }
}
