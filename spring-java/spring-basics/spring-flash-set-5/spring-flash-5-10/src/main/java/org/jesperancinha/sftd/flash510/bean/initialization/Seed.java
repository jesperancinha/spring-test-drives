package org.jesperancinha.sftd.flash510.bean.initialization;

import jakarta.annotation.PostConstruct;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.beans.factory.InitializingBean;

import static org.jesperancinha.sftd.flash510.bean.initialization.SpringFlash510Launcher.atomicInteger;

public class Seed implements InitializingBean {
    private SeedState seedState;

    private String description;

    public SeedState getSeedState() {
        return seedState;
    }

    public void setSeedState(SeedState seedState) {
        this.seedState = seedState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PostConstruct
    public void ready() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgRed("Phase %d", atomicInteger.getAndIncrement())
                .bgOrange("ready")
                .bgGreen(this)
                .reset();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConsolerizerComposer.outSpace()
                .black()
                .bgRed("Phase %d", atomicInteger.getAndIncrement())
                .bgOrange("afterPropertiesSet")
                .bgGreen(this)
                .reset();
    }

    @Override
    public String toString() {
        return "Seed{" +
                "seedState=" + seedState +
                ", description='" + description + '\'' +
                '}';
    }
}
