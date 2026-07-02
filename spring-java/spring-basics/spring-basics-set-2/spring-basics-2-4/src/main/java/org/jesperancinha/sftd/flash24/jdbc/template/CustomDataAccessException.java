package org.jesperancinha.sftd.flash24.jdbc.template;

import org.springframework.dao.DataAccessException;

public class CustomDataAccessException extends DataAccessException {
    public CustomDataAccessException(String msg) {
        super(msg);
    }

    public CustomDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
