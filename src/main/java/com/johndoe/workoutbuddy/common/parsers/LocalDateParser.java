package com.johndoe.workoutbuddy.common.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalDateParser {

    private LocalDateParser() {}

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd")
            .withLocale(Locale.UK);

    public static LocalDate fromString(String dateString) {
        return LocalDate.parse(dateString, DEFAULT_FORMATTER);
    }

    public static String toString(LocalDate date) {
        return date.format(DEFAULT_FORMATTER);
    }

}
