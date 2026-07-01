package org.jesperancinha.sftd.mastery1.french.music.audit;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.listener.AbstractAuditListener;
import org.springframework.stereotype.Component;

@Component
public class Mastery1AuditEventListener extends AbstractAuditListener {

    private final AuditEventRepository auditEventRepository;

    public Mastery1AuditEventListener(AuditEventRepository auditEventRepository) {
        this.auditEventRepository = auditEventRepository;
    }

    @Override
    protected void onAuditEvent(AuditEvent event) {
        ConsolerizerComposer
                .out(" ")
                .brightMagenta("This event has been thrown by the AbstractAuditListener ->")
                .green(event)
                .toConsoleLn();
        auditEventRepository.add(event);
    }
}

