package com.wallet.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        System.out.println(ldt);
        return ldt;
    }


    public static void main(String[] args) {
        System.out.println(getLocalDateTimeFromString("2017-06-10"));
//        System.out.println(getLocalDateTimeFromString("14-06-2017", "dd-MM-yyyy"));
    }
}
