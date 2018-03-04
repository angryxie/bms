package com.wwxn.bms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date formatDate(String pattern,String date) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }
}
