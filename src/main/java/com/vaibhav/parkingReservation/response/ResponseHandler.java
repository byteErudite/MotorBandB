package com.vaibhav.parkingReservation.response;

import java.io.Serializable;

public class ResponseHandler implements Serializable {

    private Page page;


    public ResponseHandler(Page page) {
        this.page = page;
    }

    public ResponseHandler() {
        super();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
