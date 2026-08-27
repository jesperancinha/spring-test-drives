package org.jesperancinha.spring.flash4.persistence.rest;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash4Launcher implements ApplicationRunner {
    static void main(String[] args) {
        SpringApplication.run(SpringFlash4Launcher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}
