package org.jesperancinha.spring.old.webapp.service;

import org.jesperancinha.spring.old.webapp.model.Detail;
import org.jesperancinha.spring.old.webapp.model.DetailConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailLayer2Service {

    @Autowired
    DetailService detailService;

    public Detail findDetailById(Integer id) {

        return DetailConverter.toDetail(detailService.findDetailById(id));

    }

}
