package org.jesperancinha.sftd.flash210.factory.configurations;

public class Material {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Material{" +
                "value='" + value + '\'' +
                '}';
    }
}
