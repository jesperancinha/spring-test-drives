package org.jesperancinha.sftd.topic.container.beans;

public class Bean {

    private String scientificName;

    private String name;

    private String mainColor;

    public Bean(String scientificName, String name, String mainColor) {
        this.scientificName = scientificName;
        this.name = name;
        this.mainColor = mainColor;
    }

    @Override
    public java.lang.String toString() {
        return "Bean{" +
                "scientificName=" + scientificName +
                ", name=" + name +
                ", mainColor=" + mainColor +
                '}';
    }
}
