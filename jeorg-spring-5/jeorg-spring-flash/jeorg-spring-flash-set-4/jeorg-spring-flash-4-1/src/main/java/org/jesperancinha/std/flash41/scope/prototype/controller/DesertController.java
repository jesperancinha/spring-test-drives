package org.jesperancinha.std.flash41.scope.prototype.controller;

import org.jesperancinha.std.flash41.scope.prototype.domain.Desert;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("request")
public class DesertController {

    private final Desert desert;

    public DesertController(Desert desert) {
        this.desert = desert;
    }

    @GetMapping("/")
    public Desert randomDesert(){
        desert.reachableDestroy();
        return desert;
    }
}
