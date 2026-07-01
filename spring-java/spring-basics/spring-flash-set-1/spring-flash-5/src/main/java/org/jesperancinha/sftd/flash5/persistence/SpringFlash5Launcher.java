package org.jesperancinha.sftd.flash5.persistence;

import org.jesperancinha.sftd.flash5.persistence.domain.Potato;
import org.jesperancinha.sftd.flash5.persistence.domain.PotatoService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.boot.SpringApplication.run;

@RestController
@SpringBootApplication
@EnableJpaRepositories
public class SpringFlash5Launcher {
    private PotatoService potatoService;

    public static void main(String[] args) {
        run(SpringFlash5Launcher.class, args);
    }

    public SpringFlash5Launcher(final PotatoService potatoService) {
        this.potatoService = potatoService;
    }

    @PostMapping("/")
    public void createAllPotatoes() {
        var potato1 = new Potato();
        potato1.setForm("Elephant");
        potato1.setLocalDateTime(LocalDateTime.now());
        var potato2 = new Potato();
        potato2.setForm("Cat");
        potato2.setLocalDateTime(LocalDateTime.now());
        var potato3 = new Potato();
        potato3.setForm("Giraffe");
        potato3.setLocalDateTime(LocalDateTime.now());
        potatoService.createPotato(potato1);
        potatoService.createPotato(potato2);
        potatoService.createPotato(potato3);
    }

    @GetMapping("/")
    public List<Potato> getAllPotatoes() {
        return potatoService.getAllPotatoes();
    }
}
