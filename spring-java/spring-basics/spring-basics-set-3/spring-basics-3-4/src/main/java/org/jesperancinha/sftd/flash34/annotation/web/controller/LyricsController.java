package org.jesperancinha.sftd.flash34.annotation.web.controller;

import jakarta.servlet.http.HttpServlet;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash34.annotation.web.model.LyricCollection;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@RestController
@RequestMapping(value = "/")
public class LyricsController extends HttpServlet {

    private static final long serialVersionUID = -7194019126118562416L;

    public LyricsController() {
        ConsolerizerComposer.outSpace()
                .orange(title("Analysing the AnnotationConfigWebApplicationContext"))
                .reset();
    }

    @RequestMapping(value = "/carpenters",
            method = RequestMethod.GET)
    public @ResponseBody
    String carpenters(Model model) {
        LyricCollection lyricCollection = new LyricCollection();
        lyricCollection.setBand("The Carpenters");
        lyricCollection.getLyrics().add("Top of The World");
        ConsolerizerComposer.outSpace()
                .green("Returning:")
                .orange().jsonPrettyPrint(lyricCollection)
                .reset();
        return lyricCollection.toString();
    }
}
