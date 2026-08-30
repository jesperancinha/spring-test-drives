package org.jesperancinha.spring.flash55.hateoas.controller;

import org.jesperancinha.spring.flash55.hateoas.ImmuneCells;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImmuneCellsController {

    /**
     * The idea of this endpoint is to link to /endless endpoint.
     * The latter will link back to this one
     *
     * @return {@link ImmuneCells} with a HATEOAS link
     */
    @GetMapping("/")
    public ImmuneCells getAllCells() {
        final var immuneCells = new ImmuneCells();
        immuneCells.add(Link.of("/endless"));
        return immuneCells;
    }

    /**
     * The idea of this endpoint is to link to / endpoint.
     * The latter will link back to this one
     *
     * @return {@link ImmuneCells} with a HATEOAS link
     */
    @GetMapping("/endless")
    public ImmuneCells getAllCellsEndless() {
        final var immuneCells = new ImmuneCells();
        immuneCells.add(Link.of("/"));
        return immuneCells;
    }
}
