package org.jesperancinha.sftd.flash11.form;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class Flash11Controller {

    @RequestMapping("/")
    public ModelAndView mainPage(Map<String, Object> model) {
        Music music = new Music();
        music.setArtist("Tracy Chapman");
        music.setSong("Talkin' About A Revolution");
        model.put("music", music);
        return new ModelAndView("index");
    }

    @RequestMapping("/req")
    public @ResponseBody
    String handleRequest(Model model, Music music) {
        final var sb = new StringBuilder();
        sb.append("You did it!\n");
        sb.append("\n");
        sb.append(ConsolerizerColor.GREEN.getPBEscapedText(model));
        sb.append(ConsolerizerColor.GREEN.getPBEscapedText(music));
        sb.append("<a href=\"/\">Back</a>");
        return sb.toString();
    }
}