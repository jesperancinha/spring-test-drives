package org.jesperancinha.spring.flash513.template.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.flash513.template.dto.Casket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CasketController {

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
