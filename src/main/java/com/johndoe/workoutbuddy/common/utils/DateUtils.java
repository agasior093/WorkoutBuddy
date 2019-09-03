package com.johndoe.workoutbuddy.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public final class DateUtils {

    private DateUtils() {
    }

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN)
            .withZone(ZONE_ID);

    public static LocalDate fromString(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, DEFAULT_FORMATTER) : LocalDate.now();
    }

    public static String toString(LocalDate date) {
        return date != null ? date.format(DEFAULT_FORMATTER) : null;
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(ZONE_ID);
    }

    public static LocalDate today() {
        return LocalDate.now(ZONE_ID);
    }

    public static LocalDateTime addTime(LocalDate date) {

        LocalTime midnight = LocalTime.MIDNIGHT;
        return LocalDateTime.of(date, midnight);
    }

    public static Date toJavaDate(LocalDate date) {
       try {
           SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
           isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
           return isoFormat.parse(date.toString() + "T00:00:00");
       } catch (Exception e) {
           log.error(e.getMessage());
           return null;
       }
    }

}
