package org.jesperancinha.spring.tutorial.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.jesperancinha.spring.tutorial.SpringBasicsTutorialPart1.mutateLyricsServiceImpl;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MutateLyricsServiceTest {

    @Autowired
    public ApplicationContext applicationContext;

    @Test
    void testContext() {
        mutateLyricsServiceImpl(applicationContext);
    }
}