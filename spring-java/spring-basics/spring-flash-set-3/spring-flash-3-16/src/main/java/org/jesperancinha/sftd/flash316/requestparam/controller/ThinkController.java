package org.jesperancinha.sftd.flash316.requestparam.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash316.requestparam.model.Think;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ThinkController {

    @PostMapping(path = "/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Think thinkRandom(
            @RequestBody
            final List<String> thinks,
            @RequestParam(name = "artist", required = false)
            final String artist) {
        final List<Think> thinkList = thinks.stream().map(thinkString ->
                Think.builder().artist(artist).thinkString(thinkString).build()).collect(Collectors.toList());
        final Think think = thinkList.get((int) (thinkList.size() * Math.random()));
        MAGENTA.printGenericLn("An artist is not required by default and that we need to specify. This is when we want it to be optional and not mandatory");
        ConsolerizerComposer.outSpace()
                .random()
                .jsonPrettyPrint(thinkList)
                .reset();
        return think;
    }
}
