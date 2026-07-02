package org.jesperancinha.sftd.flash25.jpa.operators.service;

import org.jesperancinha.sftd.flash25.jpa.operators.domain.Bean;
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("normal")
public class BeanServiceImpl implements BeanService {

    @Value("${org.jesperancinha.sftd.flash25.jpa.operators.slogan}")
    private String slogan;

    private BeanRepository beanRepository;

    public BeanServiceImpl(final BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    @Override
    public Bean createBean(Bean bean) {
        return this.beanRepository.save(bean);
    }

    @Override
    public List<Bean> getAll() {
        return this.beanRepository.findAll();
    }

    @Override
    public Bean getById(Long id) {
        return this.beanRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bean> getHigherThan50() {
        return this.beanRepository.findByKilosGreaterThan(50L);
    }

    @Override
    public List<Bean> getLowerThan50() {
        return this.beanRepository.findByKilosLessThan(50L);
    }

    @Override
    public List<Bean> allKidneyBeans() {
        return this.beanRepository.findByNameLike("%Kidney%");
    }

    @Override
    public List<Bean> getBetween(Long kilos1, Long kilos2) {
        return this.beanRepository.findByKilosBetween(kilos1, kilos2);
    }

    @Override
    public Bean findRunnerBean() {
        return this.beanRepository.findFirstByNameLike("%Runner%");
    }

    @Override
    public Bean findTopByKiloOrderByName(Long kilos) {
        return this.beanRepository.findDistinctTopByKilosOrderByNameAsc(kilos);
    }

    @Override
    public List<Bean> findByNameContains(String word) {
        return this.beanRepository.findByNameContains(word);
    }

    @Override
    public Bean getByName(String name) {
        return this.beanRepository.findByNameIs(name);
    }

    @Override
    public Long countBeansByNameIgnoreCase(String name) {
        return this.beanRepository.countBeansByNameIgnoreCase(name);
    }

    @Override
    public List<Bean> getNotWeighed() {
        return this.beanRepository.findByKilosNull();
    }

    public String getSlogan() {
        return slogan;
    }

    @Override
    public List<Bean> getByNameNot(String name) {
        return this.beanRepository.findByNameNot(name);
    }

    @Override
    public void deleteById(Long id) {
        this.beanRepository.deleteById(id);
    }
}
