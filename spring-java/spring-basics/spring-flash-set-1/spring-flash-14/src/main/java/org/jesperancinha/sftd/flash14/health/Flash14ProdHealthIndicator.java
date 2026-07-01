package org.jesperancinha.sftd.flash14.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

@Component
@Profile("prod")
public class Flash14ProdHealthIndicator implements HealthIndicator {

    private final String path;
    private final String file;

    @Autowired
    public Flash14ProdHealthIndicator(
            @Value("${jeorg.spring.flash14.path}")
            final String path,
            @Value("${jeorg.spring.flash14.file}")
            final String file) {
        this.path = path;
        this.file = file;
    }


    @Override
    public Health health() {
        final Path path = Path.of(this.path, this.file);
        try {
            FileStore fileStore = Files.getFileStore(Path.of(this.path));
            if (path.toFile().exists()) {
                return Health.up()
                        .withDetail("lyrics", "Quiet in the corner, Numb and falling through")
                        .withDetail("spaceUsed", fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()).build();
            }
            return Health.down()
                    .withDetail("lyrics", "Everybody is awesome!")
                    .withDetail("spaceUsed", fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()).build();
        } catch (IOException e) {
            RED.printThrowableAndExit(e);
        }
        return Health.down().build();
    }
}
