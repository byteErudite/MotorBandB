package com.vaibhav.parkingReservation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "slot_type")
public class SlotType extends BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID slotTypeId;
    private int length;
    private int breadth;
    private String typeName;
    private float hourRate;
    private float dayRate;
    private float monthRate;
    private String dimensionUnit;

    public UUID getSlotTypeId() {
        return slotTypeId;
    }

    public void setSlotTypeId(UUID slotTypeId) {
        this.slotTypeId = slotTypeId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlotType{");
        sb.append("slotTypeId=").append(slotTypeId);
        sb.append(", length=").append(length);
        sb.append(", breadth=").append(breadth);
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", hourRate=").append(hourRate);
        sb.append(", dayRate=").append(dayRate);
        sb.append(", monthRate=").append(monthRate);
        sb.append(", dimensionUnit='").append(dimensionUnit).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
