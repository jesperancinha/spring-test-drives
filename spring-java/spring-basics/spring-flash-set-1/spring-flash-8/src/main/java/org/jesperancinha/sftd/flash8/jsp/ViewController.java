package org.jesperancinha.sftd.flash8.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ViewController {

    @RequestMapping("/")
    public ModelAndView mainPage(Map<String, Object> model) {
        model.put("notification", "All I want for christmas is you!");
        return new ModelAndView("index");
    }
}