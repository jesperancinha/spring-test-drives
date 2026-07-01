package org.jesperancinha.sftd.mastery2.portuguese.music.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String nationality;
}
