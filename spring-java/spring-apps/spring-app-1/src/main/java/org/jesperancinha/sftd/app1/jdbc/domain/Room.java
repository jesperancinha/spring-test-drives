package org.jesperancinha.sftd.app1.jdbc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity
public class Room extends CruiseBase {

    @Column
    private Long roomNumber;

    @Column
    private String roomName;

    @Column
    private Long floor;

    @Column
    private Long areaInMeters;

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Long getAreaInMeters() {
        return areaInMeters;
    }

    public void setAreaInMeters(Long areaInMeters) {
        this.areaInMeters = areaInMeters;
    }
}
