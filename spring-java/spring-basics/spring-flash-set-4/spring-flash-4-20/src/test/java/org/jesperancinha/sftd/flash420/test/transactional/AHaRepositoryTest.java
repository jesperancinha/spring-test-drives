package org.jesperancinha.sftd.flash420.test.transactional;

import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AHaRepositoryTest {

    @Autowired
    private AHaRepository aHaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Consolerizer.setupFastDefaultWideTitleSpread();
        final List<AHaSong> all = aHaRepository.findAll();
        ConsolerizerComposer.out(" ")
                .magenta("There should be a constant number\nof elements on the database since everything else is rolled back after completion")
                .red("rolled back")
                .magenta("!!!")
                .newLine()
                .blue("And so the result is")
                .brightBlue(all)
                .newLine()
                .green("We have")
                .green(all.size())
                .green("elements!")
                .toConsoleLn();
        assertThat(all).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void createSong1() {
        final var aHaSong = new AHaSong();
        aHaSong.setName("Stay on these roads");
        aHaRepository.save(aHaSong);
    }

    @Test
    void createSong2() {
        final var aHaSong = new AHaSong();
        aHaSong.setName("The Sun always shines on tv");
        aHaRepository.save(aHaSong);
    }
}