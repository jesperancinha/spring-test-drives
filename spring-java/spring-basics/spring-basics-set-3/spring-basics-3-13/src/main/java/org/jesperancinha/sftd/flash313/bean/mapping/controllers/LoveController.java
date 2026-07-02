package org.jesperancinha.sftd.flash313.bean.mapping.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Component("/love.html")
public class LoveController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) {
        final var modelAndView = new ModelAndView("love");
        modelAndView.addObject("message", "Love is stronger than death");
        ConsolerizerComposer.outSpace()
                .cyan()
                .jsonPrettyPrint(modelAndView)
                .reset();
        return modelAndView;
    }
}