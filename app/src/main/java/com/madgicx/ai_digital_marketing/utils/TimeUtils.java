package com.madgicx.ai_digital_marketing.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {

    public static String FORMAT__ = "yyyy.MM.dd.E";
    public static String DATE_FORMAT_UTC_GLOBAL = "yyyy-MM-dd HH:mm:ss";

    public static String formatUTCToLocalDateTime(long timeLong, String format) {
        long value = convertUTCToLocalTime(timeLong);
        DateFormat oldFormatter = new SimpleDateFormat(format, Locale.US);
        return oldFormatter.format(value);
    }

    private static long convertUTCToLocalTime(long timestampMs) {
        TimeZone localZone = TimeZone.getDefault();
        long offset = localZone.getOffset(timestampMs);
        return timestampMs + offset;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime(String format) {
        DateFormat time = new SimpleDateFormat(format);
        return time.format(new Date());
    }
}
