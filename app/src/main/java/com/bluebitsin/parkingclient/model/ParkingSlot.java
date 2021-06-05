package com.bluebitsin.parkingclient.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingSlot implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("floorId")
    @Expose
    private Integer floorId;
    @SerializedName("slotNumber")
    @Expose
    private Integer slotNumber;
    @SerializedName("wingCode")
    @Expose
    private String wingCode;
    @SerializedName("isSlotBooked")
    @Expose
    private Integer isSlotBooked;
    public final static Creator<ParkingSlot> CREATOR = new Creator<ParkingSlot>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ParkingSlot createFromParcel(android.os.Parcel in) {
            return new ParkingSlot(in);
        }

        public ParkingSlot[] newArray(int size) {
            return (new ParkingSlot[size]);
        }

    }
            ;

    protected ParkingSlot(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.floorId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.slotNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.wingCode = ((String) in.readValue((String.class.getClassLoader())));
        this.isSlotBooked = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ParkingSlot() {
    }

    /**
     *
     * @param floorId
     * @param wingCode
     * @param slotNumber
     * @param id
     * @param isSlotBooked
     */
    public ParkingSlot(Integer id, Integer floorId, Integer slotNumber, String wingCode, Integer isSlotBooked) {
        super();
        this.id = id;
        this.floorId = floorId;
        this.slotNumber = slotNumber;
        this.wingCode = wingCode;
        this.isSlotBooked = isSlotBooked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getWingCode() {
        return wingCode;
    }

    public void setWingCode(String wingCode) {
        this.wingCode = wingCode;
    }

    public Integer getIsSlotBooked() {
        return isSlotBooked;
    }

    public void setIsSlotBooked(Integer isSlotBooked) {
        this.isSlotBooked = isSlotBooked;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(floorId);
        dest.writeValue(slotNumber);
        dest.writeValue(wingCode);
        dest.writeValue(isSlotBooked);
    }

    public int describeContents() {
        return 0;
    }

}
