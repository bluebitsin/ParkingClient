package com.bluebitsin.parkingclient.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Helper {

    public static Date getDateTime() {

        try {

            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String currentTime = sdf.format(dt);

            return sdf.parse(currentTime);
        } catch (ParseException e) {

            //e.printStackTrace();
            return null;
        }
    }

    public static String getFormattedDateTime(String timestamp){

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssX");

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        try {
            return outputFormat.format(inputFormat.parse(timestamp));
        }catch (ParseException e){
            return null;
        }

    }

    public static String[] exploadDateTime(String formattedDate) {
        return formattedDate.split(", ");
    }

    public static String ticketNumber(String reservationId){

        String[] splitId = reservationId.split("-");
        return splitId[4].toUpperCase();
    }
}
