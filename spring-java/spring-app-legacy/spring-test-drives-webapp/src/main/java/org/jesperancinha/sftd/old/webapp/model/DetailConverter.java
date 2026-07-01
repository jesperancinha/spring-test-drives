package org.jesperancinha.sftd.old.webapp.model;

/**
 * Created by joao on 15-5-16.
 */
public class DetailConverter {

    public static Detail toDetail(DetailEntity detailEntity) {
        return Detail.builder()
                .name(detailEntity.getName())
                .city(detailEntity.getCity())
                .build();
    }
}
