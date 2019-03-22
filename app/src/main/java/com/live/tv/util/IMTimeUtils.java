package com.live.tv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by taoh on 2018/5/30.
 */

public class IMTimeUtils {

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long time = (System.currentTimeMillis() - timeStamp) / 1000;
        //LogHelper.d("convertTimeToFormat--->",String.valueOf(time)+"  传进来的时间为："+String.valueOf(timeStamp));
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if(time >= 3600 * 24 && time <= 3600 * 24 * 30){
            return time / (3600 * 24) + "天前";
        } else if(time >= 3600 * 24*30 && time <= 3600 * 24 * 30*12){
            return time / (3600 * 24*30) + "月前";
        } else{
            return "未知";
        }
    } /**
     * 将一个时间字符串转成毫秒
     *
     * @param timeStamp
     * @return
     */
    public static long StringTimeToMillisecond(String timeStamp) {

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }

       return calendar.getTimeInMillis();
    }

}
