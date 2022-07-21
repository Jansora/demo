package com.jansora.utils;

import com.jansora.infrastructure.dto.DateDto;
import com.jansora.infrastructure.dto.DatetimeDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <Description> Description for DateUtils <br>
 *
 * @author jansora <br>
 * @version 1.0 <br>
 * @github https://github.com/Jansora
 * @CreateDate 2021/5/16 20:55:11 <br>
 * @since 1.0 <br>
 */
public class DateUtils {

    private static final SimpleDateFormat tableDf = new SimpleDateFormat("yyyy_MM");

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatNow() {
        return df.format(new Date());
    }

    public static String format(Date date) {
        return df.format(date);
    }

    public static String format(DateDto date) {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
    }

    public static String formatToday() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); // same for minutes and seconds
        return df.format(today);
    }

    public static String formatThisMonth() {
        return tableDf.format(new Date());
    }


    public static DateDto today() {
        Calendar today = Calendar.getInstance();
        return formatCalendar(today);
    }

    public static DateDto yesterday() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, -1);
        return formatCalendar(today);
    }

    public static DateDto theDayBeforeYesterday() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, -2);
        return formatCalendar(today);
    }

    private static DatetimeDto formatCalendar(Calendar calendar) {

        DatetimeDto date = new DatetimeDto();
        date.setYear(calendar.get(Calendar.YEAR));
        date.setMonth(calendar.get(Calendar.MONTH) + 1);
        date.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        date.setHour(calendar.get(Calendar.HOUR));
        date.setMinute(calendar.get(Calendar.MINUTE));
        date.setSecond(calendar.get(Calendar.SECOND));
        return date;
    }

}
