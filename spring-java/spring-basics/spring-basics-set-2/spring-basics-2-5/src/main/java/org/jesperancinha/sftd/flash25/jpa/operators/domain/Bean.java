package org.jesperancinha.sftd.flash25.jpa.operators.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long kilos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKilos() {
        return kilos;
    }

    public void setKilos(Long kilos) {
        this.kilos = kilos;
    }
}
