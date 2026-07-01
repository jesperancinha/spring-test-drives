package org.jesperancinha.sftd.topic.container.beans;

public class BeanOnlyRead {

    private String scientificName;

    private String name;

    private String mainColor;

    public BeanOnlyRead(String scientificName, String name, String mainColor) {
        this.scientificName = scientificName;
        this.name = name;
        this.mainColor = mainColor;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "scientificName=" + scientificName +
                ", name=" + name +
                ", mainColor=" + mainColor +
                '}';
    }

    public String getMainColor() {
        return mainColor;
    }

    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }
}
