package org.jesperancinha.sftd.action.mvc.dto;

public class Capacitors extends ElectronicPart {

    private CapacitorType capacitorType;

    private Long capacity;

    private CapacitorUnit capacitorUnit;

    public Capacitors() {

    }

    public Capacitors(Long quantity, String reference, CapacitorType capacitorType, Long capacity, CapacitorUnit capacitorUnit) {
        super(quantity, reference);
        this.capacitorType = capacitorType;
        this.capacity = capacity;
        this.capacitorUnit = capacitorUnit;
    }

    public CapacitorType getCapacitorType() {
        return capacitorType;
    }

    public void setCapacitorType(CapacitorType capacitorType) {
        this.capacitorType = capacitorType;
    }
}
