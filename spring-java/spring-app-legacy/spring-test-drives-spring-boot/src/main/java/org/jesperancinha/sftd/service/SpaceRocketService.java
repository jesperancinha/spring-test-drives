package org.jesperancinha.sftd.service;

import org.jesperancinha.sftd.entities.SpaceRocket;
import org.jesperancinha.sftd.entities.SpaceRocketRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jesperancinha on 24-5-16.
 */
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
