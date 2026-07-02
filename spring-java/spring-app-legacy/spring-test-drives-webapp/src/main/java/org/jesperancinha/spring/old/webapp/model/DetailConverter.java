package org.jesperancinha.spring.old.webapp.model;

public class DetailConverter {

    public static Detail toDetail(DetailEntity detailEntity) {
        return Detail.builder()
                .name(detailEntity.getName())
                .city(detailEntity.getCity())
                .build();
    }
}
