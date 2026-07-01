package org.jesperancinha.sftd.mastery3.plants;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.mastery3.plants.configuration.JmpConfigBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Mastery3Plants implements ApplicationRunner {

    private final JmpConfigBean jmpConfigBean;

    public Mastery3Plants(JmpConfigBean jmpConfigBean) {
        this.jmpConfigBean = jmpConfigBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Mastery3Plants.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ConsolerizerComposer
                .outSpace()
                .green("This plant is a %s, and it measures %dm tall from the base. It is only %s years old!",
                        jmpConfigBean.getPlant(),
                        jmpConfigBean.getHeight(),
                        jmpConfigBean.getLifespan())
                .reset();
    }
}

