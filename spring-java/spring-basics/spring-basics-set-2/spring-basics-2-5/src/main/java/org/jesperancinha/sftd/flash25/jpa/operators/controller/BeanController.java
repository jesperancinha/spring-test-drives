package org.jesperancinha.sftd.flash25.jpa.operators.controller;

import org.jesperancinha.sftd.flash25.jpa.operators.domain.Bean;
import org.jesperancinha.sftd.flash25.jpa.operators.service.BeanService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BeanController {

    private final BeanService beanService;

    public BeanController(
            @Qualifier("normal")
            final BeanService beanService) {
        this.beanService = beanService;
    }

    @GetMapping(path = "/",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getAllBeans() {
        return beanService.getAll();
    }

    @GetMapping(path = "/{id}",
            produces = APPLICATION_JSON_VALUE)
    public Bean getBeanById(
            @PathVariable
            final Long id) {
        return beanService.getById(id);
    }

    @GetMapping(path = "/bean/{name}",
            produces = APPLICATION_JSON_VALUE)
    public Bean getBeanByName(
            @PathVariable
            final String name) {
        return beanService.getByName(name);
    }

    @GetMapping(path = "/bean/not/{name}",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getBeanByNameNot(
            @PathVariable
            final String name) {
        return beanService.getByNameNot(name);
    }

    @DeleteMapping(path = "/bean/{id}",
            produces = APPLICATION_JSON_VALUE)
    public void deleteById(
            @PathVariable
            final Long id) {
        beanService.deleteById(id);
    }

    @GetMapping(path = "/bean/case/{name}",
            produces = APPLICATION_JSON_VALUE)
    public Long countBeansByNameIgnoringCase(
            @PathVariable
            final String name) {
        return beanService.countBeansByNameIgnoreCase(name);
    }

    @GetMapping(path = "/bean/not/weighed",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getBeansNotWeighed() {
        return beanService.getNotWeighed();
    }

    @GetMapping(path = "/high50",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getBeansHigherThan50() {
        return beanService.getHigherThan50();
    }

    @GetMapping(path = "/low50",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getBeansLowerThan50() {
        return beanService.getLowerThan50();
    }

    @GetMapping(path = "/kidneys",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getAllKidneys() {
        return beanService.allKidneyBeans();
    }

    @GetMapping(path = "/between/{lower}/{higher}",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getBetween(
            @PathVariable
            final Long lower,
            @PathVariable
            final Long higher) {
        return beanService.getBetween(lower, higher);
    }

    @GetMapping(path = "/runner",
            produces = APPLICATION_JSON_VALUE)
    public Bean getRunner() {
        return beanService.findRunnerBean();
    }

    @GetMapping(path = "/first/{kilos}",
            produces = APPLICATION_JSON_VALUE)
    public Bean getFirstOrderedByName(
            @PathVariable
            final Long kilos) {
        return beanService.findTopByKiloOrderByName(kilos);
    }

    @GetMapping(path = "/contains/{word}",
            produces = APPLICATION_JSON_VALUE)
    public List<Bean> getContains(
            @PathVariable
            final String word) {
        return beanService.findByNameContains(word);
    }
}
