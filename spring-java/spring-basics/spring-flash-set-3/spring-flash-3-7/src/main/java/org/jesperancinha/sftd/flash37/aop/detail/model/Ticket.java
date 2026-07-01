package org.jesperancinha.sftd.flash37.aop.detail.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
@Data
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String artist;

    @Column
    private String show;

    @Column
    private LocalDateTime localDateTime;

    @Column
    private UUID uuid;
}
