package org.jesperancinha.sftd.action.mvc2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class JeorgActionMvcLauncher implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(JeorgActionMvcLauncher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @GetMapping(path = "/doc",
            produces = MediaType.TEXT_HTML_VALUE)
    public String askDocument(Model model) {
        model.addAttribute("wow", "opah");
        return "doc";
    }
}
