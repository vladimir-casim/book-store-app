package com.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SAVING_BOOK = "yyyy-MM-dd-HH-mm-ss";
    public static SimpleDateFormat dateFormater = new SimpleDateFormat(DATE_FORMAT);
    public static SimpleDateFormat dateFormaterSivinBook = new SimpleDateFormat(DATE_FORMAT_SAVING_BOOK);

    private DateFormatter(){
    }

    public static String format(Date date) {
        return dateFormater.format(date);
    }
    public static String formatDateForBookName(Date date) {
        return dateFormaterSivinBook.format(date);
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
