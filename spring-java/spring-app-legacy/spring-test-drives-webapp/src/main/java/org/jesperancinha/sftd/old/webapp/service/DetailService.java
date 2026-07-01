package org.jesperancinha.sftd.old.webapp.service;

import org.jesperancinha.sftd.old.webapp.model.DetailEntity;
import org.jesperancinha.sftd.old.webapp.repository.DetailRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }


    /**
     * @param id The id used to retrieve {@link DetailEntity}
     * @return {@link DetailEntity}
     */
    @Cacheable(value = "detailCache",
            key = "#id")
    public DetailEntity findDetailById(Integer id) {
        return detailRepository.findById(id).orElse(null);
    }
}
