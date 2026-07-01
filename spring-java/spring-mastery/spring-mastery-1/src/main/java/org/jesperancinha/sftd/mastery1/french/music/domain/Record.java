package org.jesperancinha.sftd.mastery1.french.music.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate publicationDate;

    @OneToOne
    private Label label;

    @OneToOne
    private Artist artist;

    @OneToMany
    private List<Song> songList;
}
