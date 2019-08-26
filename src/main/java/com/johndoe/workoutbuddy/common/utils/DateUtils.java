package com.johndoe.workoutbuddy.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {}

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN)
            .withZone(ZONE_ID);

    public static LocalDate fromString(String dateString) {
        return LocalDate.parse(dateString, DEFAULT_FORMATTER);
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
}
