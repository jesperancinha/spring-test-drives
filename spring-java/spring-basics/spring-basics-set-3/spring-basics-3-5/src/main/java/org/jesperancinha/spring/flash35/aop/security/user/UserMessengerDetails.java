package org.jesperancinha.spring.flash35.aop.security.user;


public class UserMessengerDetails {
    private String userName;

    public UserMessengerDetails(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserMessengerDetails{" +
                "userName='" + userName + '\'' +
                '}';
    }
}