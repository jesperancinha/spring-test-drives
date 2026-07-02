package org.jesperancinha.sftd.flash57.secured.controller;

import org.jesperancinha.sftd.flash57.secured.domain.Throne;
import org.jesperancinha.sftd.flash57.secured.dto.ThroneDto;
import org.jesperancinha.sftd.flash57.secured.services.ThroneService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

@RestController
public class Flash57Controller {

    private final ThroneService throneService;

    public Flash57Controller(ThroneService throneService) {
        this.throneService = throneService;
    }

    @GetMapping("/thrones")
    public List<Throne> listThrones() {
        return throneService.getAll();
    }

    @GetMapping("/dance")
    public String dance() {
        return throneService.dance();
    }

    @GetMapping("/thrones/{id}")
    public Throne jewel(
            @PathVariable("id")
                    Long id) {
        return throneService.getThrone(id);
    }

    @PostMapping(path = "/thrones",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThroneDto> Jewel(
            @RequestBody
                    ThroneDto throneDto) {
        Throne throne = new Throne();
        throne.setThroneType(throneDto.getThroneType());
        throne.setKeeper(throneDto.getKeeper());
        final ThroneDto jewel1 = throneService.createThrone(throne);
        GREEN.printGenericLn("Created jewel -> %s", jewel1);
        return ResponseEntity.ok().body(jewel1);
    }

    @DeleteMapping("/thrones/{id}")
    public void removeJewel(
            @PathVariable("id")
                    Long id) {
        final Throne throneById = throneService.getThrone(id);
        throneService.deleteThrone(throneById);
        RED.printGenericLn("Removed jewel -> %d", id);
    }
}
