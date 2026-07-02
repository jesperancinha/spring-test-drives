package org.jesperancinha.sftd.flash14.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Profile("test")
public class Flash14HealthIndicator extends AbstractHealthIndicator {

    private final String path;
    private final String file;

    @Autowired
    public Flash14HealthIndicator(
            @Value("${jeorg.spring.flash14.path}")
            final String path,
            @Value("${jeorg.spring.flash14.file}")
            final String file) throws IOException {
        this.path = path;
        this.file = file;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        final var path = Path.of(this.path, this.file);
        FileStore fileStore = Files.getFileStore(Path.of(this.path));

        if (path.toFile().exists()) {
            builder.up()
                    .withDetail("lyric", "You've got some kind of family there to turn to and that's more than I could ever give to you")
                    .withDetail("spaceUsed", fileStore.getTotalSpace() - fileStore.getUnallocatedSpace());
        } else {
            builder.down()
                    .withDetail("lyric", "A chance for calm, A hope for freedom")
                    .withDetail("spaceUsed", fileStore.getTotalSpace() - fileStore.getUnallocatedSpace());
        }
    }
}
