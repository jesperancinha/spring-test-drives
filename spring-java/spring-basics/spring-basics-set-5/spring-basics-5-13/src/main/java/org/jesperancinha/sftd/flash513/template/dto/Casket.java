package org.jesperancinha.sftd.flash513.template.dto;

public class Casket {

    private String designation;

    private long units;

    private String brand;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Casket{" +
                "designation='" + designation + '\'' +
                ", units=" + units +
                ", brand='" + brand + '\'' +
                '}';
    }
}
