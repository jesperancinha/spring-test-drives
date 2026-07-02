package org.jesperancinha.spring.action.aop.fishing;

import org.jesperancinha.spring.action.aop.model.Harverst;
import org.springframework.stereotype.Service;

@Service
public interface Harvester {

    <T extends Harverst> T harvest();
}
