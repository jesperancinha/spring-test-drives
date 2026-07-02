package org.jesperancinha.sftd.flash29.security.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash29.security.dto.JewelDto;
import org.jesperancinha.sftd.flash29.security.services.JewelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;

@Controller
public class Flash29Controller {

    private final JewelService jewelService;

    public Flash29Controller(JewelService jewelService) {
        this.jewelService = jewelService;
    }

    @RequestMapping("/")
    public ModelAndView genericHandle(final Model model) {
        final var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        YELLOW.printGenericLn("This is the object we are analyzing %s", authentication);
        model
                .addAttribute("name", authentication.getName())
                .addAttribute("roles", authentication.getAuthorities());
        return new ModelAndView("ownedjewels");
    }

    @GetMapping("/jewels")
    public
    @ResponseBody
    List<JewelDto> listJewels() {
        return jewelService.getAll().stream().map(jewel ->
                JewelDto.builder()
                        .jewelType(jewel.getJewelType())
                        .guardian(jewel.getGuardian())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/jewels/{id}")
    public
    @ResponseBody
    JewelDto jewel(
            @PathVariable("id")
                    Long id) {
        return jewelService.getJewelById(id);
    }

    @PostMapping(path = "/jewels",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JewelDto> Jewel(
            @RequestBody
                    JewelDto jewelDto) {
        ConsolerizerComposer.outSpace()
                .blue("Received:")
                .yellow()
                .jsonPrettyPrint(jewelDto)
                .reset();
        final JewelDto createdJewel = jewelService.createJewel(jewelDto);
        GREEN.printGenericLn("Created jewel -> %s", createdJewel);
        return ResponseEntity.ok().body(createdJewel);
    }

    /**
     * This method is here for pure demonstration purposes
     * The goal is to check the getJewel method in combination with a delete method in order to explore authentication
     *
     * @param id The id of the Jewel {@link Long}
     */
    @DeleteMapping("/jewels/{id}")
    public @ResponseBody
    void removeJewel(
            @PathVariable("id")
                    Long id) {
        final JewelDto jewelDto = jewelService.getJewelById(id);
        ConsolerizerComposer.outSpace()
                .blue("Deleting the following jeeel:")
                .yellow()
                .jsonPrettyPrint(jewelDto)
                .reset();
        jewelService.deleteJewel(jewelDto);
        RED.printGenericLn("Removed jewel -> %d", id);
    }
}
