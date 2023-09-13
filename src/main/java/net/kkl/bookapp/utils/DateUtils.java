package net.kkl.bookapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getNowString() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }
    public static long getNowDate() {
        String nowDate = getNowString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long date = 0;
        try {
            date =simpleDateFormat.parse(nowDate).getTime()/1000;
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    public static String getNowDateToString(long dateLong) {
        Date date = new Date(dateLong * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String createdAt = simpleDateFormat.format(date);
        return createdAt;
    }


    public static String getNowDateToStringInSystem() {
        Date date = new Date(getNowDate() * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String createdAt = simpleDateFormat.format(date);
        return createdAt;
    }
}
