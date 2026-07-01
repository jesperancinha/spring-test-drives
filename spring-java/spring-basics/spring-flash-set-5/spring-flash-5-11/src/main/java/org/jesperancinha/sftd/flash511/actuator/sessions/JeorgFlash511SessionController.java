package org.jesperancinha.sftd.flash511.actuator.sessions;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeorgFlash511SessionController {

    private final JeorgFlash511SessionService jeorgFlash511SessionService;

    public JeorgFlash511SessionController(JeorgFlash511SessionService jeorgFlash511SessionService) {
        this.jeorgFlash511SessionService = jeorgFlash511SessionService;
    }

    @GetMapping(path = "actuator/sessions",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JeorgFlash511SessionActuatorData listActiveSessions() {
        return new JeorgFlash511SessionActuatorData("Active Sessions", jeorgFlash511SessionService.getSessions());
    }

}