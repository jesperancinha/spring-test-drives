package org.jesperancinha.sftd.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRocket {

    @Id
    private Integer id;

    private String name;

    // In meters
    private double height;

    private String engineType;

    // In Kg
    private double payLoad;

}
