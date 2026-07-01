package org.jesperancinha.sftd.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jesperancinha on 24-5-16.
 */
@Repository
public interface SpaceRocketRepository extends JpaRepository<SpaceRocket, Integer> {

    List<SpaceRocket> findSpaceRocketByName(
            @Param("name")
                    String name);
}
