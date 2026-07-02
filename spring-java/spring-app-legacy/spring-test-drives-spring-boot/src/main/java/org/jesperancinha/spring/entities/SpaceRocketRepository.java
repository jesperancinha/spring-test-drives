package org.jesperancinha.spring.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRocketRepository extends JpaRepository<SpaceRocket, Integer> {

    List<SpaceRocket> findSpaceRocketByName(
            @Param("name")
                    String name);
}
