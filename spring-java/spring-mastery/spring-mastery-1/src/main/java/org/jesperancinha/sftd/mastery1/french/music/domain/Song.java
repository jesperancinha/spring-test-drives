package org.jesperancinha.sftd.mastery1.french.music.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Song {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private Long trackNumber;

    private String name;

    private String shortLyric;

    @OneToOne
    private Artist artist;

    private LocalDate recordingDate;

    @OneToMany
    private List<Record> records;
}
