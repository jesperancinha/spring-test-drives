package org.jesperancinha.sftd.action.aop.fishing;

import org.jesperancinha.sftd.action.aop.model.Harverst;
import org.springframework.stereotype.Service;

@Service
public interface Harvester {

    <T extends Harverst> T harvest();
}
