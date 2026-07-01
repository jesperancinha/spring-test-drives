package org.jesperancinha.sftd.topic.container.beans;

import java.beans.ConstructorProperties;

public class BeanWithContructor {

    private String scientificName;

    private String name;

    private String mainColor;

    @ConstructorProperties({"scientificName", "name", "mainColor"})
    public BeanWithContructor(String scientificName, String name, String mainColor) {
        this.scientificName = scientificName;
        this.name = name;
        this.mainColor = mainColor;
    }

    @Override
    public String toString() {
        return "BeanWithContructor{" +
                "scientificName=" + scientificName +
                ", name=" + name +
                ", mainColor=" + mainColor +
                '}';
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getName() {
        return name;
    }

    public String getMainColor() {
        return mainColor;
    }
}
