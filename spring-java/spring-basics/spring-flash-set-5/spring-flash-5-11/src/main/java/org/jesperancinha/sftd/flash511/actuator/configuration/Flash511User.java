package org.jesperancinha.sftd.flash511.actuator.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Flash511User {
    private String name;
    private String password;
    private List<String> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flash511User)) return false;
        Flash511User flash511User = (Flash511User) o;
        return getName().equals(flash511User.getName()) && getPassword().equals(flash511User.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class FlashUserBuilder {
        private String name;
        private String password;
        private List<String> roles;

        public static FlashUserBuilder flashUserBuilder() {
            return new FlashUserBuilder();
        }

        public FlashUserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FlashUserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public FlashUserBuilder roles(String... roles) {
            this.roles = Arrays.asList(roles);
            return this;
        }

        public Flash511User build() {
            final var flashUser = new Flash511User();
            flashUser.setName(name);
            flashUser.setPassword(password);
            flashUser.setRoles(roles);
            return flashUser;
        }
    }
}
