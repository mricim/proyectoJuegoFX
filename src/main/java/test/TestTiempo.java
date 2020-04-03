package main.java.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

import static java.time.temporal.ChronoUnit.DAYS;

public class TestTiempo {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter fmtStrict = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter fmtLenient = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withResolverStyle(ResolverStyle.LENIENT);
    private static final DateTimeFormatter fmtSmart = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withResolverStyle(ResolverStyle.SMART);


    private static final DateTimeFormatter newStrict = resolverWich(ResolverStyle.STRICT);
    private static final DateTimeFormatter newLenient = resolverWich(ResolverStyle.LENIENT);
    private static final DateTimeFormatter newSmart = resolverWich(ResolverStyle.SMART);




    public static final ZoneId ZONE_UTC = ZoneOffset.UTC;
    public static final ZoneId ZONE_GTM = ZoneId.systemDefault();

    public static void main(String[] args) {
        LocalDateTime utc = LocalDateTime.now(ZONE_UTC);
        LocalDateTime utcMasHoras = utc.plusHours(3);
        LocalDateTime gmt = changeZone(utc);
        LocalDateTime gmtMasHoras = changeZone(utcMasHoras);
        System.out.println(utc);
        System.out.println(gmt);
        System.out.println(gmt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(utcMasHoras);
        System.out.println(gmtMasHoras);


        DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder().appendPattern("uuuu/MM/dd[ [HH][:mm][:ss][.SSS]]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        LocalDateTime d2 = LocalDateTime.parse("2019/04/09 02:37:00", DATE_FORMAT);

        DateTimeFormatter original = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter novo = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);

    }

    public static LocalDateTime changeZone(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(ZONE_GTM).toLocalDateTime();
    }


    public int duration(String initialDate, String endDate) {

        return (int) DAYS.between(LocalDate.parse(initialDate, dateTimeFormatter), LocalDate.parse(endDate, dateTimeFormatter));

    }

    public static DateTimeFormatter resolverWich(ResolverStyle resolverStyle) {
        return DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withResolverStyle(resolverStyle);
    }
}
