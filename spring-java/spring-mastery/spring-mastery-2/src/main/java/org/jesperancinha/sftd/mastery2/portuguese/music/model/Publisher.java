package org.jesperancinha.sftd.mastery2.portuguese.music.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private LocalDate foundationDate;

}
