package org.jesperancinha.spring.service;

import org.jesperancinha.spring.entities.SpaceRocket;
import org.jesperancinha.spring.entities.SpaceRocketRepository;
import org.springframework.stereotype.Service;

@Service
public class SpaceRocketService {

    final
    SpaceRocketRepository spaceRocketRepository;

    public SpaceRocketService(SpaceRocketRepository spaceRocketRepository) {
        this.spaceRocketRepository = spaceRocketRepository;
    }

    public Iterable<SpaceRocket> getAllRockets() {
        return spaceRocketRepository.findAll();
    }

    public void deleteRocket(final Integer id) {
        spaceRocketRepository.deleteById(id);
    }

    public void addRocket(final SpaceRocket spaceRocket) {
        spaceRocketRepository.save(spaceRocket);
    }

}
