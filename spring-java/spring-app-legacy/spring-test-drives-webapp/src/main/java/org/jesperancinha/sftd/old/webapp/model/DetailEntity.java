package org.jesperancinha.sftd.old.webapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Cacheable("detailCache")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "detail",
        schema = "public")
@ToString
public class DetailEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Transient
    private String city;

}
