package org.jesperancinha.spring.tutorial.persistence.rest;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringFlash4Launcher implements ApplicationRunner {
    static void main(String[] args) {
        SpringApplication.run(SpringFlash4Launcher.class, args);
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
}
