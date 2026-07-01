package org.jesperancinha.sftd.flash26.jdbc.handlers.model;

public class Shell {

    private String name;

    private String scientificName;

    private String predominantColor;

    public Shell(String name, String scientificName, String predominantColor) {
        this.name = name;
        this.scientificName = scientificName;
        this.predominantColor = predominantColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getPredominantColor() {
        return predominantColor;
    }

    public void setPredominantColor(String predominantColor) {
        this.predominantColor = predominantColor;
    }

    @Override
    public String toString() {
        return "Shell{" +
                "name='" + name + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", predominantColor='" + predominantColor + '\'' +
                '}';
    }
}
