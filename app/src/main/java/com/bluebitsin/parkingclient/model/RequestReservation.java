package com.bluebitsin.parkingclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestReservation implements Serializable {

    @SerializedName("customerId")
    @Expose
    private Integer userId;

    public RequestReservation() {
    }

    public RequestReservation(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
