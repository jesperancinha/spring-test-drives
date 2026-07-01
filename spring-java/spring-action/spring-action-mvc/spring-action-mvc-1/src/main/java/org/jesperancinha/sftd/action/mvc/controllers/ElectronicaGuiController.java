package org.jesperancinha.sftd.action.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gui")
public class ElectronicaGuiController {

    @GetMapping
    public String index() {
        return "index";
    }
}
