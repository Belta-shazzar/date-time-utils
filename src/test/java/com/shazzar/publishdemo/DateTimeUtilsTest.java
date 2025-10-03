package com.shazzar.publishdemo;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

  @Test
  void testIsToday() {
    assertTrue(DateTimeUtils.isToday(LocalDate.now()));
    assertFalse(DateTimeUtils.isToday(LocalDate.now().plusDays(1)));
  }

  @Test
  void testIsPast() {
    assertTrue(DateTimeUtils.isPast(LocalDate.now().minusDays(1)));
    assertFalse(DateTimeUtils.isPast(LocalDate.now().plusDays(1)));
  }

  @Test
  void testIsFuture() {
    assertTrue(DateTimeUtils.isFuture(LocalDate.now().plusDays(1)));
    assertFalse(DateTimeUtils.isFuture(LocalDate.now().minusDays(1)));
  }

  @Test
  void testDaysBetween() {
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 1, 11);
    assertEquals(10, DateTimeUtils.daysBetween(start, end));
  }

  @Test
  void testYearsBetween() {
    LocalDate start = LocalDate.of(2020, 1, 1);
    LocalDate end = LocalDate.of(2024, 1, 1);
    assertEquals(4, DateTimeUtils.yearsBetween(start, end));
  }

  @Test
  void testToReadableStringDate() {
    LocalDate date = LocalDate.of(2024, 1, 15);
    String result = DateTimeUtils.toReadableString(date);
    assertEquals("January 15, 2024", result);
  }

  @Test
  void testToReadableStringDateTime() {
    LocalDateTime dateTime = LocalDateTime.of(2024, 1, 15, 15, 30);
    String result = DateTimeUtils.toReadableString(dateTime);

    assertTrue(result.contains("January 15, 2024"));
    assertTrue(result.contains("3:30 p.m."));
  }

  @Test
  void testStartOfDay() {
    LocalDate date = LocalDate.of(2024, 1, 15);
    LocalDateTime result = DateTimeUtils.startOfDay(date);
    assertEquals(0, result.getHour());
    assertEquals(0, result.getMinute());
    assertEquals(0, result.getSecond());
  }

  @Test
  void testEndOfDay() {
    LocalDate date = LocalDate.of(2024, 1, 15);
    LocalDateTime result = DateTimeUtils.endOfDay(date);
    assertEquals(23, result.getHour());
    assertEquals(59, result.getMinute());
    assertEquals(59, result.getSecond());
  }

  @Test
  void testIsWeekend() {
    // January 6, 2024 is a Saturday
    LocalDate saturday = LocalDate.of(2024, 1, 6);
    assertTrue(DateTimeUtils.isWeekend(saturday));

    // January 8, 2024 is a Monday
    LocalDate monday = LocalDate.of(2024, 1, 8);
    assertFalse(DateTimeUtils.isWeekend(monday));
  }

  @Test
  void testIsWeekday() {
    LocalDate monday = LocalDate.of(2024, 1, 8);
    assertTrue(DateTimeUtils.isWeekday(monday));

    LocalDate saturday = LocalDate.of(2024, 1, 6);
    assertFalse(DateTimeUtils.isWeekday(saturday));
  }

  @Test
  void testNextBusinessDay() {
    // Friday
    LocalDate friday = LocalDate.of(2024, 1, 5);
    LocalDate nextBusiness = DateTimeUtils.nextBusinessDay(friday);
    // Should be Monday
    assertEquals(DayOfWeek.MONDAY, nextBusiness.getDayOfWeek());
    assertEquals(LocalDate.of(2024, 1, 8), nextBusiness);
  }

  @Test
  void testCalculateAge() {
    LocalDate birthDate = LocalDate.now().minusYears(25);
    assertEquals(25, DateTimeUtils.calculateAge(birthDate));
  }

  @Test
  void testGetDatesBetween() {
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 1, 5);
    List<LocalDate> dates = DateTimeUtils.getDatesBetween(start, end);

    assertEquals(5, dates.size());
    assertEquals(start, dates.get(0));
    assertEquals(end, dates.get(4));
  }

  @Test
  void testFirstDayOfMonth() {
    LocalDate date = LocalDate.of(2024, 1, 15);
    LocalDate firstDay = DateTimeUtils.firstDayOfMonth(date);
    assertEquals(1, firstDay.getDayOfMonth());
  }

  @Test
  void testLastDayOfMonth() {
    LocalDate date = LocalDate.of(2024, 1, 15);
    LocalDate lastDay = DateTimeUtils.lastDayOfMonth(date);
    assertEquals(31, lastDay.getDayOfMonth());
  }

  @Test
  void testTimestampConversion() {
    LocalDateTime now = LocalDateTime.now().withNano(0);
    long timestamp = DateTimeUtils.toTimestamp(now);
    LocalDateTime converted = DateTimeUtils.fromTimestamp(timestamp);

    assertEquals(now.withNano(0), converted.withNano(0));
  }

  @Test
  void testUtilityClassCannotBeInstantiated() throws Exception {
    var constructor = DateTimeUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    Exception exception = assertThrows(Exception.class, constructor::newInstance);
    Throwable cause = exception.getCause();

    assertInstanceOf(UnsupportedOperationException.class, cause);
    assertEquals("Utility class cannot be instantiated", cause.getMessage());
  }
}