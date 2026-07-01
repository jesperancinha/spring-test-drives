package org.jesperancinha.sftd.flash511.actuator.sessions;

import jakarta.servlet.http.HttpSession;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.time.Instant.ofEpochMilli;

public class JeorgFlash511SessionData {

    private final Map<String, Object> parameterValues;

    private final String id;

    private final String createDate;

    private final String lastAccess;


    public JeorgFlash511SessionData(HttpSession httpSession) {
        this.parameterValues = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(httpSession.getAttributeNames()
                        .asIterator(), Spliterator.ORDERED),
                false)
                .collect(Collectors.toMap(
                        attributeName -> attributeName,
                        httpSession::getAttribute
                ));

        this.id = httpSession.getId();
        this.createDate = formatDateTime(httpSession.getCreationTime());
        this.lastAccess = formatDateTime(httpSession.getLastAccessedTime());
    }

    private String formatDateTime(long epoch) {
        final var instant = ofEpochMilli(epoch);
        final var zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public Map<String, Object> getParameterValues() {
        return parameterValues;
    }

    public String getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLastAccess() {
        return lastAccess;
    }
}
