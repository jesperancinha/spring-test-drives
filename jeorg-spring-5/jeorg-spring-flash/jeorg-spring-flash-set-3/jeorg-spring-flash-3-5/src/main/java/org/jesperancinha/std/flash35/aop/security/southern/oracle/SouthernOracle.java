package org.jesperancinha.std.flash35.aop.security.southern.oracle;

import org.jesperancinha.std.flash35.aop.security.user.UserMessengerDetails;

public class SouthernOracle {

    private static ThreadLocal<UserMessengerDetails> threadLocal = new ThreadLocal<>();

    public void login(String userName) {
        threadLocal.set(new UserMessengerDetails(userName));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public UserMessengerDetails getLoggedOnUser() {
        return threadLocal.get();
    }
}


