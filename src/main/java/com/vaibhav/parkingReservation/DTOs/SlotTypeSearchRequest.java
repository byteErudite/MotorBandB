package com.vaibhav.parkingReservation.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotTypeSearchRequest {

    private Set<String> slotTypeIds;
    private Integer length;
    private Integer breadth;
    private String typeName;
    private Float hourRate;
    private Float dayRate;
    private Float monthRate;

    public SlotTypeSearchRequest() {
    }

    public Set<String> getSlotTypeIds() {
        return slotTypeIds;
    }

    public void setSlotTypeIds(Set<String> slotTypeIds) {
        this.slotTypeIds = slotTypeIds;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getBreadth() {
        return breadth;
    }

    public void setBreadth(Integer breadth) {
        this.breadth = breadth;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Float getHourRate() {
        return hourRate;
    }

    public void setHourRate(Float hourRate) {
        this.hourRate = hourRate;
    }

    public Float getDayRate() {
        return dayRate;
    }

    public void setDayRate(Float dayRate) {
        this.dayRate = dayRate;
    }

    public Float getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(Float monthRate) {
        this.monthRate = monthRate;
    }
}
