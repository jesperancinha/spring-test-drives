package org.jesperancinha.sftd.flash16.authority.domain;

import java.util.Objects;

public class FlashUser {
    private String name;
    private String password;
    private String role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashUser)) return false;
        FlashUser flashUser = (FlashUser) o;
        return getName().equals(flashUser.getName()) && getPassword().equals(flashUser.getPassword());
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public static class FlashUserBuilder {
        private String name;
        private String password;
        private String role;

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

        public FlashUserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public FlashUser build() {
            final var flashUser = new FlashUser();
            flashUser.setName(name);
            flashUser.setPassword(password);
            flashUser.setRole(role);
            return flashUser;
        }
    }
}
