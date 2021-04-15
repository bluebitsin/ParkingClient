package com.bluebitsin.parkingclient.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParkingLot implements Serializable {

    @SerializedName("id")
    private int parkingLotId;
    private String name;
    private String address;
    @SerializedName("no_of_blocks")
    private int totalBlocks;
    @SerializedName("zip")
    private int zipcode;
    @SerializedName("operating_company_name")
    private String operatingCompany;

    public ParkingLot(int parkingLotId, String name, String address, int totalBlocks, int zipcode, String operatingCompany) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.address = address;
        this.totalBlocks = totalBlocks;
        this.zipcode = zipcode;
        this.operatingCompany = operatingCompany;
    }

    public ParkingLot() {
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getOperatingCompany() {
        return operatingCompany;
    }

    public void setOperatingCompany(String operatingCompany) {
        this.operatingCompany = operatingCompany;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "parkingLotId=" + parkingLotId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalBlocks=" + totalBlocks +
                ", zipcode=" + zipcode +
                ", operatingCompany='" + operatingCompany + '\'' +
                '}';
    }
}
