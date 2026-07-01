package org.jesperancinha.sftd.flash214.transactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private Integer carYear;

    @Column
    @Convert(converter = MovieAppearanceConverter.class)
    private String[] movieAppearances;

    @Transient
    public Car clone() {
        return new CarBuilder()
                .model(this.model)
                .brand(this.brand)
                .carYear(this.carYear)
                .movieAppearances(this.movieAppearances)
                .build();
    }
}
