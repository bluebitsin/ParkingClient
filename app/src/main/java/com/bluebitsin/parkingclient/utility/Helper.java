package com.bluebitsin.parkingclient.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
