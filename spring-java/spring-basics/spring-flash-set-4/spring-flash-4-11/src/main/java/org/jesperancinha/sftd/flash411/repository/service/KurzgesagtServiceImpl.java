package org.jesperancinha.sftd.flash411.repository.service;

import org.jesperancinha.sftd.flash411.repository.domain.Kurzgesagt;
import org.jesperancinha.sftd.flash411.repository.repository.KurzgesagtRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KurzgesagtServiceImpl implements KurzgesagtService {

    private final KurzgesagtRepository kurzgesagtRepository;

    public KurzgesagtServiceImpl(KurzgesagtRepository kurzgesagtRepository) {
        this.kurzgesagtRepository = kurzgesagtRepository;
    }

    @Override
    public List<Kurzgesagt> getAll() {
        return kurzgesagtRepository.findAll();
    }

    @Override
    public Kurzgesagt create(Kurzgesagt kurzgesagt) {
        return kurzgesagtRepository.save(kurzgesagt);
    }
}
