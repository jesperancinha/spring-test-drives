package org.jesperancinha.spring.flash43.security.context.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class SecretController {

    @RequestMapping("/")
    public String revealSecret(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping("/origin")
    public String revealPartOfTheSecret(Map<String, Object> model) {
        return "index";
    }
}
