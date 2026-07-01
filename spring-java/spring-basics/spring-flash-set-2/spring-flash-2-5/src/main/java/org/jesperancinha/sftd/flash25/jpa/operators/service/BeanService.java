package org.jesperancinha.sftd.flash25.jpa.operators.service;

import org.jesperancinha.sftd.flash25.jpa.operators.domain.Bean;

import java.util.List;

public interface BeanService {

    Bean createBean(Bean bean);

    List<Bean> getAll();

    List<Bean> allKidneyBeans();

    Bean getById(Long id);

    Bean findRunnerBean();

    List<Bean> getHigherThan50();

    List<Bean> getLowerThan50();

    List<Bean> getBetween(Long kilos1, Long kilos2);

    Bean findTopByKiloOrderByName(Long kilos);

    List<Bean> findByNameContains(String word);

    Bean getByName(String name);

    Long countBeansByNameIgnoreCase(String name);

    List<Bean> getNotWeighed();

    String getSlogan();

    List<Bean> getByNameNot(String name);

    void deleteById(Long id);
}

