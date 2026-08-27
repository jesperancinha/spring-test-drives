package org.jesperancinha.spring.action.mvc2.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocController {

    @GetMapping(path = "/doc",
            produces = MediaType.TEXT_HTML_VALUE)
    public String askDocument(Model model) {
        model.addAttribute("wow", "opah");
        return "doc";
    }
}
