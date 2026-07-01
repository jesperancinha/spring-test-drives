package org.jesperancinha.sftd.flash5.persistence.domain;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotatoService {

    private PotatoRepository potatoRepository;

    public PotatoService(PotatoRepository potatoRepository) {
        this.potatoRepository = potatoRepository;
    }

    public Potato createPotato(final Potato potato) {
        return potatoRepository.save(potato);
    }

    public List<Potato> getAllPotatoes() {
        return potatoRepository.findAll();
    }
}
