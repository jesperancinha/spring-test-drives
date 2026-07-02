package org.jesperancinha.sftd.flash513.template;

import jakarta.servlet.http.HttpServletResponse;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash513.template.dto.Casket;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.quote;

@RestController
@RequestMapping("api")
@SpringBootApplication
public class SpringFlash513Launcher implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash513Launcher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        final var restTemplate = new RestTemplate();
        final var casket = new Casket();
        casket.setBrand("Chiquita");
        casket.setDesignation("Bananas");
        casket.setUnits(100);
        sentWithNoStatus(restTemplate, casket);
        sentWithStatus(restTemplate, casket);
        sentWithUrl(restTemplate, casket);


    }

    private void sentWithUrl(RestTemplate restTemplate, Casket casket) {
        final var uri = URI.create("http://localhost:8081/api/casket");
        final URI uriResult = restTemplate.postForLocation(uri, casket);
        ConsolerizerComposer.outSpace()
                .yellow("We don't get access neither to our casket nor to the status")
                .cyan("We do get the url location, which comes in header")
                .cyan(quote("Location"))
                .orange(uriResult)
                .reset();
    }

    private void sentWithStatus(RestTemplate restTemplate, Casket casket) {
        final var uri = URI.create("http://localhost:8081/api/casket");
        final ResponseEntity<Casket> casketResponseEntity = restTemplate.postForEntity(uri, casket, Casket.class);
        ConsolerizerComposer.outSpace()
                .yellow("We received our casket back!")
                .red("Status is %s", casketResponseEntity.getStatusCode())
                .orange(casketResponseEntity.getBody())
                .reset();
    }

    private void sentWithNoStatus(RestTemplate restTemplate, Casket casket) {
        final var uri = URI.create("http://localhost:8081/api/casket");
        final Casket casketReturn = restTemplate.postForObject(uri, casket, Casket.class);
        ConsolerizerComposer.outSpace()
                .yellow("We received our casket back!")
                .orange(casketReturn)
                .reset();
    }

    @PostMapping(path = "casket")
    public Casket sendCasket(
            @RequestBody
            final Casket casket, HttpServletResponse httpServletResponse) {
        ConsolerizerComposer.outSpace()
                .brightBlue("We just received your casket filled with")
                .brightGreen(casket)
                .reset();
        httpServletResponse.addHeader("Location", "http://joaofilipesabinoesperancinha.nl");
        return casket;
    }
}
