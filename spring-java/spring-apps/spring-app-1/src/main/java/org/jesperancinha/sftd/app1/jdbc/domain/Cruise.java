package org.jesperancinha.sftd.app1.jdbc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity
public class Cruise extends CruiseBase {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
