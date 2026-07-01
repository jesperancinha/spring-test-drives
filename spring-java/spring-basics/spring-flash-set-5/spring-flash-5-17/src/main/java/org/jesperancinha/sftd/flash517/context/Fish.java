package org.jesperancinha.sftd.flash517.context;

public class Fish {

    private String name;

    public Fish() {

    }

    public Fish(String name) {
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
        return "Fish{" +
                "name='" + name + '\'' +
                '}';
    }
}
