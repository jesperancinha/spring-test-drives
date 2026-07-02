package org.jesperancinha.sftd.flash511.actuator.sessions;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

public class JeorgFlash511SessionActuatorData {


    private String info;

    private int activeSessions;

    private final List<JeorgFlash511SessionData> sessions;

    public JeorgFlash511SessionActuatorData(String info, List<HttpSession> httpSessions) {
        this.info = info;
        this.activeSessions = httpSessions.size();
        this.sessions = httpSessions.stream()
                .map(this::getSessionState)
                .collect(Collectors.toList());
    }

    private JeorgFlash511SessionData getSessionState(HttpSession httpSession) {
        return new JeorgFlash511SessionData(httpSession);
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(int activeSessions) {
        this.activeSessions = activeSessions;
    }

    public List<JeorgFlash511SessionData> getSessions() {
        return sessions;
    }
}