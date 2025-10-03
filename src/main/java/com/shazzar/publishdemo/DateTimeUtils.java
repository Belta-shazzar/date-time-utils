package com.shazzar.publishdemo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class providing convenient methods for common date and time operations.
 * 
 * @author Daniel Oguejiofor
 * @version 1.0.0
 * @since 1.0.0
 */
public class DateTimeUtils {

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Checks if a given date is today.
     *
     * @param date the date to check
     * @return true if the date is today, false otherwise
     */
    public static boolean isToday(LocalDate date) {
        return date.equals(LocalDate.now());
    }

    /**
     * Checks if a given date is in the past.
     *
     * @param date the date to check
     * @return true if the date is in the past, false otherwise
     */
    public static boolean isPast(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Checks if a given date is in the future.
     *
     * @param date the date to check
     * @return true if the date is in the future, false otherwise
     */
    public static boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Calculates the number of days between two dates.
     *
     * @param start the start date
     * @param end the end date
     * @return the number of days between the dates
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calculates the number of years between two dates.
     *
     * @param start the start date
     * @param end the end date
     * @return the number of years between the dates
     */
    public static long yearsBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.YEARS.between(start, end);
    }

    /**
     * Formats a LocalDateTime to a human-readable string.
     * Example: "January 15, 2024, at 3:30 PM"
     *
     * @param dateTime the datetime to format
     * @return formatted string
     */
    public static String toReadableString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' h:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Formats a LocalDate to a human-readable string.
     * Example: "January 15, 2024"
     *
     * @param date the date to format
     * @return formatted string
     */
    public static String toReadableString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return date.format(formatter);
    }

    /**
     * Gets the start of day for a given date.
     *
     * @param date the date
     * @return LocalDateTime at the start of the day (00:00:00)
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    /**
     * Gets the end of day for a given date.
     *
     * @param date the date
     * @return LocalDateTime at the end of the day (23:59:59)
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(23, 59, 59);
    }

    /**
     * Checks if a date falls on a weekend (Saturday or Sunday).
     *
     * @param date the date to check
     * @return true if the date is a weekend, false otherwise
     */
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Checks if a date falls on a weekday (Monday through Friday).
     *
     * @param date the date to check
     * @return true if the date is a weekday, false otherwise
     */
    public static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

    /**
     * Gets the next business day (skips weekends).
     *
     * @param date the starting date
     * @return the next business day
     */
    public static LocalDate nextBusinessDay(LocalDate date) {
        LocalDate nextDay = date.plusDays(1);
        while (isWeekend(nextDay)) {
            nextDay = nextDay.plusDays(1);
        }
        return nextDay;
    }

    /**
     * Calculates age from a birthdate.
     *
     * @param birthDate the birthdate
     * @return age in years
     */
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Gets all dates between two dates (inclusive).
     *
     * @param start the start date
     * @param end the end date
     * @return list of all dates between start and end
     */
    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;
        
        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }
        
        return dates;
    }

    /**
     * Gets the first day of the month for a given date.
     *
     * @param date the date
     * @return the first day of the month
     */
    public static LocalDate firstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * Gets the last day of the month for a given date.
     *
     * @param date the date
     * @return the last day of the month
     */
    public static LocalDate lastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * Converts a timestamp in milliseconds to LocalDateTime.
     *
     * @param timestamp the timestamp in milliseconds
     * @return LocalDateTime representation
     */
    public static LocalDateTime fromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp), 
            ZoneId.systemDefault()
        );
    }

    /**
     * Converts LocalDateTime to timestamp in milliseconds.
     *
     * @param dateTime the LocalDateTime
     * @return timestamp in milliseconds
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}