package org.jesperancinha.std.flash34.annotation.web.controller;

import org.jesperancinha.std.flash34.annotation.web.model.LyricCollection;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping(value = "/")
public class LyricsController extends HttpServlet {

    private static final long serialVersionUID = -7194019126118562416L;

    @RequestMapping(value = "/carpenters",
            method = RequestMethod.GET)
    public @ResponseBody
    String carpenters(Model model) throws Exception {
        LyricCollection lyricCollection = new LyricCollection();
        lyricCollection.setBand("The Carpenters");
        lyricCollection.getLyrics().add("Top of The World");
        return lyricCollection.toString();
    }
}
