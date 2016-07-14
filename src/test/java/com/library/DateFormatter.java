package com.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static SimpleDateFormat dateFormater = new SimpleDateFormat(DATE_FORMAT);

    private DateFormatter(){
    }

    public static String format(Date date) {
        return dateFormater.format(date);
    }

    public static Date parseDate(String date) {
        Date parsedDate = null;
        try {
            parsedDate = dateFormater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
