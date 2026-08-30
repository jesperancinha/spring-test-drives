package org.jesperancinha.spring.tutorial;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.tutorial.service.LyricsService;
import org.jesperancinha.spring.tutorial.service.LyricsServiceImpl;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBasicsTutorialPart1 implements ApplicationRunner {
    static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(SpringBasicsTutorialPart1.class, args);
        mutateLyricsServiceImpl(ac);
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        ConsolerizerComposer.outSpace()
                .yellow("We provide the annotation this way:")
                .blue("""
                              <dependency>
                                    <groupId>org.springframework.boot</groupId>
                                    <artifactId>spring-boot-test</artifactId>
                                    <scope>test</scope>
                                </dependency>\
                        """)
                .yellow("We provide the implementation this way:")
                .blue("""
                                <dependency>
                                    <groupId>org.springframework</groupId>
                                    <artifactId>spring-test</artifactId>
                                    <scope>test</scope>
                                </dependency>\
                        """)
                .reset();
    }


   public static void mutateLyricsServiceImpl(ApplicationContext ac) {
        LyricsService lyricsService = ac.getBean("lyricsServiceImpl", LyricsServiceImpl.class);
        try {
            try {
                lyricsService.enumerateLyric1();
            } finally {
                try {
                    lyricsService.resultLyric1();
                } finally {
                    try {
                        lyricsService.enumerateLyric2();
                    } finally {
                        try {
                            lyricsService.resultLyric2();
                        } finally {
                            try {
                                lyricsService.enumerateLyric3();
                            } finally {
                                try {
                                    lyricsService.resultLyric3();
                                } finally {
                                    lyricsService.resultLyric4();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            ConsolerizerComposer.outSpace().red(e).reset();
        }
    }
}
