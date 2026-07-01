package org.jesperancinha.sftd.flash55.hateoas;


import org.springframework.hateoas.RepresentationModel;

import java.util.Arrays;
import java.util.List;

public class ImmuneCells extends RepresentationModel<ImmuneCells> {

    private List<String> cells = Arrays.asList(
            "Macrophage", "Neutrophil", "Natural Killer Cell", "Complement", "Mast Cell", "Monocyte", "Follicular Dentritic Cell");

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }
}
