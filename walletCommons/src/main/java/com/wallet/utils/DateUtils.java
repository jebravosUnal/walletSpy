package com.wallet.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class DateUtils {


    public static LocalDateTime getLocalDateTimeFromString(String stringDate){
        return getLocalDateTimeFromString(stringDate, "yyyy-MM-dd");
    }

    public static LocalDateTime getLocalDateTimeFromString(String stringDate, String pattern){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern(pattern);
        LocalDate ld = LocalDate.parse(stringDate, DATEFORMATTER);
        LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
//        System.out.println(ldt);
        return ldt;
    }

    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime){
        if(localDateTime == null){
            throw new IllegalArgumentException("localDateTime should not be null");
        }

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getLocalDateTimeFromDate(Date date){
        return getLocalDateTimeFomDate(date, null);
    }

    public static LocalDateTime getLocalDateTimeFomDate(Date date, ZoneId zoneId){
        if(date == null){
            throw new IllegalArgumentException("date should not be null");
        }

        return LocalDateTime.ofInstant(date.toInstant(), zoneId != null ? zoneId : ZoneId.systemDefault());
    }

    public static Date addDays(Date date, int days){
        LocalDateTime newDate = getLocalDateTimeFromDate(date);
        return getDateFromLocalDateTime(newDate.plusDays(days));
    }

    public static Date addMonths(Date date, int months){
        LocalDateTime newDate = getLocalDateTimeFromDate(date);
        return getDateFromLocalDateTime(newDate.plusMonths(months));
    }

    public static Date addYears(Date date, int years){
        LocalDateTime newDate = getLocalDateTimeFromDate(date);
        return getDateFromLocalDateTime(newDate.plusYears(years));
    }

    public static Long until(Date fromDate, Date untilDate, TemporalUnit unit){
        return getLocalDateTimeFromDate(fromDate).until(getLocalDateTimeFromDate(untilDate), unit);
    }


    public static void main(String[] args) {
        System.out.println(getLocalDateTimeFromString("2017-06-10"));
//        System.out.println(getLocalDateTimeFromString("14-06-2017", "dd-MM-yyyy"));
    }
}
