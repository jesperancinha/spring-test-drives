package org.jesperancinha.sftd.action.aop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bonito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
