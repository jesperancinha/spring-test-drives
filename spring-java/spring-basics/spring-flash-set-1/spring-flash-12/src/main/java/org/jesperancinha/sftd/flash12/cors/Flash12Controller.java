package org.jesperancinha.sftd.flash12.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Flash12Controller {

    @GetMapping("/cors")
    @CrossOrigin(origins = "http://spring-flash-12:8081")
    public String sentence() {
        return "When accessing from spring-flash-12, there should be no blocking to visualization";
    }

    @GetMapping("/always")
    public String sentenceAlways() {
        return "This should be blocked from the opposite url.";
    }

    @GetMapping("/protected")
    public String sentenceProtected() {
        return "When accessing from spring-flash-12, there should be no blocking to visualization";
    }


}