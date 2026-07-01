package org.jesperancinha.sftd.app1.jdbc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CruiseBase {

    @Id
    @Column(updatable = false,
            unique = true)
    private Long id;

    private String tourOperator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(String tourOperator) {
        this.tourOperator = tourOperator;
    }
}
