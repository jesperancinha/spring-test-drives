package org.jesperancinha.b2b2springboot.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jesperancinha on 24-5-16.
 */
@Repository
public interface SpaceRocketRepository extends JpaRepository<SpaceRocket, Integer> {

    List<SpaceRocket> findSpaceRocketByName(@Param("name") String name);
}
