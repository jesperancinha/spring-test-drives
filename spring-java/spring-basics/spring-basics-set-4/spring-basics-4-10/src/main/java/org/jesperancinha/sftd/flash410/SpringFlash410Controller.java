package org.jesperancinha.sftd.flash410;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringFlash410Controller {
    @RequestMapping(value = {"/", "/welcome**"},
            method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Ava Max - My Head & My Heart");
        model.addObject("message", "This is the original video clip");
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/admin**",
            method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Ava Max - My Head & My Heart(Bimini Bon Boulash Performance Video)");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error",
                    required = false)
                    String error,
            @RequestParam(value = "logout",
                    required = false)
                    String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid credentials!");
        }
        if (logout != null) {
            model.addObject("msg", "You have been logged out!");
        }
        model.setViewName("login");
        return model;
    }
}
