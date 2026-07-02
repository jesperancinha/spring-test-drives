package org.jesperancinha.sftd.flash29.security.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jesperancinha.sftd.flash29.security.services.JewelType;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jewel {
    @Id
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private JewelType jewelType;

    @Column
    private String guardian;
}
