package org.jesperancinha.sftd.flash511.actuator.sessions;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class JeorgFlash511SessionListener implements HttpSessionListener {

    private final JeorgFlash511SessionService jeorgFlash511SessionService;

    public JeorgFlash511SessionListener(JeorgFlash511SessionService jeorgFlash511SessionService) {
        this.jeorgFlash511SessionService = jeorgFlash511SessionService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        jeorgFlash511SessionService.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        jeorgFlash511SessionService.removeSession(se.getSession());
    }
}