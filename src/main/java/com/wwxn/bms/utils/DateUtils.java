package com.wwxn.bms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parseDate(String pattern,String date) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }

    public static String formatDate(String pattern,Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    public static String formatDate(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
