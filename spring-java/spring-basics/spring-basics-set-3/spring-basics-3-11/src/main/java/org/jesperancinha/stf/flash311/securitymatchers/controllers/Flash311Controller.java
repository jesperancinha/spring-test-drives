package org.jesperancinha.stf.flash311.securitymatchers.controllers;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Flash311Controller {

    @RequestMapping("/admin/**")
    public ModelAndView genericHandleAdmin(final Model model) {
        final var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        ConsolerizerComposer.outSpace()
                .yellow("This is the object we are analyzing:")
                .jsonPrettyPrint(authentication)
                .reset();
        model
                .addAttribute("name", authentication.getName())
                .addAttribute("roles", authentication.getAuthorities());
        return new ModelAndView("redpill");
    }

    @RequestMapping("/normal/**")
    public ModelAndView genericHandleNormal(final Model model) {
        final var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        ConsolerizerComposer.outSpace()
                .yellow("This is the object we are analyzing:")
                .jsonPrettyPrint(authentication)
                .reset();
        model
                .addAttribute("name", authentication.getName())
                .addAttribute("roles", authentication.getAuthorities());
        return new ModelAndView("userdata");
    }

    @RequestMapping("/user/**")
    public ModelAndView genericHandleUser(final Model model) {
        final var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        ConsolerizerComposer.outSpace()
                .yellow("This is the object we are analyzing:")
                .jsonPrettyPrint(authentication)
                .reset();
        model
                .addAttribute("name", authentication.getName())
                .addAttribute("roles", authentication.getAuthorities());
        return new ModelAndView("bluepill");
    }
}
