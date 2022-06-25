package com.example.PgReport.commonFunction;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateService {

    //EEEE- day
    //MMMM- month
    public static String getTodayDateInParticularType(String instance){
        SimpleDateFormat dayFormat = new SimpleDateFormat(instance);

        java.util.Date date = new java.util.Date();

        return  dayFormat.format(date);

    }

    public static Date currDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("UTC")));
        try {
            return formatter.parse(formatter.format(cal.getTime()));
        } catch (ParseException e) {
           // logger.error("Date Parsing Error", e);
        }
        return null;
    }


}
