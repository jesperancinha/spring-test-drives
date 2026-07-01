package org.jesperancinha.sftd.flash57.secured;

import org.jesperancinha.sftd.flash57.secured.domain.Throne;
import org.jesperancinha.sftd.flash57.secured.repository.ThroneRepository;
import org.jesperancinha.sftd.flash57.secured.services.ThroneType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash57Launcher implements CommandLineRunner {

    private final ThroneRepository throneRepository;

    public SpringFlash57Launcher(ThroneRepository throneRepository) {
        this.throneRepository = throneRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash57Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Throne queenThrone = new Throne();
        queenThrone.setThroneType(ThroneType.SAVANNAH_WOOD);
        queenThrone.setKeeper("queen");
        throneRepository.save(queenThrone);
        final Throne kingThrone = new Throne();
        kingThrone.setThroneType(ThroneType.SILK_PINK_VELVET);
        kingThrone.setKeeper("king");
        throneRepository.save(kingThrone);
    }
}
