package org.jesperancinha.sftd.flash.aop;

public class Seed {
    private String name;

    public Seed(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "name='" + name + '\'' +
                '}';
    }
}
