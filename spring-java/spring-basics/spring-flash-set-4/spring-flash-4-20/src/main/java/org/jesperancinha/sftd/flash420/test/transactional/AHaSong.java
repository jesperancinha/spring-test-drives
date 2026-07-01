package org.jesperancinha.sftd.flash420.test.transactional;

import jakarta.persistence.*;

@Entity
@Table
public class AHaSong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

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

    @Override
    public String toString() {
        return "AHaSong{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
