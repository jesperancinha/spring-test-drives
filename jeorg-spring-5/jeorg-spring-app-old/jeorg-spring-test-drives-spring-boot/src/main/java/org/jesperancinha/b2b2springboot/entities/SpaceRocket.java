package org.jesperancinha.b2b2springboot.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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
