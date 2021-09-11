package com.vaibhav.parkingReservation.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vaibhav.parkingReservation.entity.BaseEntity;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotTypeDTO extends BaseEntity implements Serializable {
    private UUID slotTypeId;
    private int length;
    private int breadth;
    private String typeName;
    private float hourRate;
    private float dayRate;
    private float monthRate;
    private String dimensionUnit;
    private UUID parkingGarageId;

    public SlotTypeDTO() {
    }

    public SlotTypeDTO(UUID slotTypeId, int length, int breadth, String typeName, float hourRate, float dayRate, float monthRate, String dimensionUnit, UUID parkingGarageId) {
        this.slotTypeId = slotTypeId;
        this.length = length;
        this.breadth = breadth;
        this.typeName = typeName;
        this.hourRate = hourRate;
        this.dayRate = dayRate;
        this.monthRate = monthRate;
        this.dimensionUnit = dimensionUnit;
        this.parkingGarageId = parkingGarageId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getHourRate() {
        return hourRate;
    }

    public void setHourRate(float hourRate) {
        this.hourRate = hourRate;
    }

    public float getDayRate() {
        return dayRate;
    }

    public void setDayRate(float dayRate) {
        this.dayRate = dayRate;
    }

    public float getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(float monthRate) {
        this.monthRate = monthRate;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public UUID getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(UUID parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public UUID getSlotTypeId() {
        return slotTypeId;
    }

    public void setSlotTypeId(UUID slotTypeId) {
        this.slotTypeId = slotTypeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlotTypeDTO{");
        sb.append("slotTypeId=").append(slotTypeId);
        sb.append("length=").append(length);
        sb.append(", breadth=").append(breadth);
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", hourRate=").append(hourRate);
        sb.append(", dayRate=").append(dayRate);
        sb.append(", monthRate=").append(monthRate);
        sb.append(", dimensionUnit='").append(dimensionUnit).append('\'');
        sb.append(", parkingGarageId='").append(parkingGarageId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
