package org.jesperancinha.sftd.flash5.persistence.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
@SequenceGenerator(
        name = "potatoSequenceGenerator",
        sequenceName = "potatoSequence",
        initialValue = 50,
        allocationSize = 15)
public class Potato {

    @Id
    @GeneratedValue(generator = "potatoSequenceGenerator")
    @Column(unique = true)
    private Long id;

    @Column
    private String form;

    @Column
    private LocalDateTime localDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Potato)) return false;
        Potato potato = (Potato) o;
        return Objects.equals(getId(), potato.getId()) && Objects.equals(getForm(), potato.getForm()) && Objects.equals(getLocalDateTime(), potato.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getForm(), getLocalDateTime());
    }
}
