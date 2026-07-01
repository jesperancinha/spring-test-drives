package org.jesperancinha.sftd.flash.aop;

public class Plant {
    private String name;

    public Plant(final String name) {
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
        return "Plant{" +
                "name='" + name + '\'' +
                '}';
    }
}
