package org.jesperancinha.spring.flash411.repository.service;

import org.jesperancinha.spring.flash411.repository.domain.Kurzgesagt;

import java.util.List;

public interface KurzgesagtService {
    public List<Kurzgesagt> getAll();

    public Kurzgesagt create(Kurzgesagt kurzgesagt);
}
