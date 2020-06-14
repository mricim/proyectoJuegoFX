package main.java.utils;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Time {
    private static final ZoneId ZONE_UTC = ZoneOffset.UTC;
    private static final ZoneId ZONE_GMT = ZoneId.systemDefault();
    public static LocalDateTime horaActual_UTC = LocalDateTime.now(ZONE_UTC);
    public static LocalDateTime horaActual_GMT = LocalDateTime.now(ZONE_GMT);


    public static LocalDateTime changeZone(LocalDateTime localDateTime, ZoneId zoneOld, ZoneId zoneNew) {
        return localDateTime.atZone(zoneOld).withZoneSameInstant(zoneNew).toLocalDateTime();
    }

    public static LocalDateTime changeZoneUTC_GMT(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(ZONE_GMT).toLocalDateTime();
    }

    public static LocalDateTime changeZoneGMT_UTC(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(ZONE_GMT).toLocalDateTime();
    }

    public static boolean isPositive(LocalDateTime initialDate, LocalDateTime endDate) {
        long i = SECONDS.between(initialDate, endDate);
        return i > 0;
    }

    public static String getChronomether(LocalDateTime toDateTime) {
        return getChronomether(horaActual_UTC, toDateTime);
    }

    public static String getChronomether(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        if (!isPositive(fromDateTime,toDateTime)){
            throw new DateTimeException("La fecha desde: "+fromDateTime+" es mayor que la fecha: "+toDateTime);
        }
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);
        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);
        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);
        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        StringBuilder stringBuilder = new StringBuilder();
        if (years > 0) {
            stringBuilder.append(years).append(" years ");
        }
        if (months > 0) {
            stringBuilder.append(months).append(" months ");
        }
        if (days > 0) {
            stringBuilder.append(days).append(" days ");
        }
        if (hours > 0) {
            stringBuilder.append(hours).append(" hours ");
        }
        if (minutes > 0) {
            stringBuilder.append(minutes).append(" minutes ");
        }
        if (seconds > 0 && hours < 1) {
            stringBuilder.append(seconds).append(" seconds ");
        }
        return stringBuilder.toString().trim().replaceAll(" +"," ");
    }
}
