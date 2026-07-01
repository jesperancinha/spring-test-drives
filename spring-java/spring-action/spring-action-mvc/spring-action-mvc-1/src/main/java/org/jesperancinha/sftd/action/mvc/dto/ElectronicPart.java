package org.jesperancinha.sftd.action.mvc.dto;

public abstract class ElectronicPart {

    private Long quantity;

    private String Reference;

    public ElectronicPart() {

    }

    public ElectronicPart(Long quantity, String reference) {
        this.quantity = quantity;
        Reference = reference;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}
