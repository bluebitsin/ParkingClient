package com.bluebitsin.parkingclient.model;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingTicket implements Parcelable
{

    @SerializedName("reservationId")
    @Expose
    private String reservationId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("slotNumber")
    @Expose
    private Integer slotNumber;
    @SerializedName("floor")
    @Expose
    private Integer floor;
    @SerializedName("wing")
    @Expose
    private String wing;
    @SerializedName("reservationStatus")
    @Expose
    private Integer reservationStatus;
    public final static Creator<ParkingTicket> CREATOR = new Creator<ParkingTicket>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ParkingTicket createFromParcel(android.os.Parcel in) {
            return new ParkingTicket(in);
        }

        public ParkingTicket[] newArray(int size) {
            return (new ParkingTicket[size]);
        }

    }
            ;

    protected ParkingTicket(android.os.Parcel in) {
        this.reservationId = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.slotNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.floor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.wing = ((String) in.readValue((String.class.getClassLoader())));
        this.reservationStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ParkingTicket() {
    }

    /**
     *
     * @param date
     * @param reservationId
     * @param slotNumber
     * @param location
     * @param floor
     * @param wing
     * @param reservationStatus
     */
    public ParkingTicket(String reservationId, String date, String location, Integer slotNumber, Integer floor, String wing, Integer reservationStatus) {
        super();
        this.reservationId = reservationId;
        this.date = date;
        this.location = location;
        this.slotNumber = slotNumber;
        this.floor = floor;
        this.wing = wing;
        this.reservationStatus = reservationStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getWing() {
        return wing;
    }

    public void setWing(String wing) {
        this.wing = wing;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(reservationId);
        dest.writeValue(date);
        dest.writeValue(location);
        dest.writeValue(slotNumber);
        dest.writeValue(floor);
        dest.writeValue(wing);
        dest.writeValue(reservationStatus);
    }

    public int describeContents() {
        return 0;
    }

}
