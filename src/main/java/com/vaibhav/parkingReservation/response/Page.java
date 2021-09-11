package com.vaibhav.parkingReservation.response;

import java.io.Serializable;

public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
    private Integer number;


    public Page(final Integer size, final Integer totalElements, final Integer page) {
        super();
        this.size = size;
        this.totalElements = totalElements;
        if (totalElements % size == 0) {
            this.totalPages = totalElements / size;
        } else {
            this.totalPages = totalElements / size + 1;
        }
        this.number = page;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
