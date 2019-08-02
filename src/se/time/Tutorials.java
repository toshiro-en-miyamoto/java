package se.time;

import java.io.Console;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class Tutorials
{
    public static void main(String[] args)
    {
        localDate();
        localTime();
        zoneId();
        zonedDateTime();
        offsetDateTime();
        // skipped Instant class
        parse();
    }

    static void parse()
    {
        Console console = System.console();
        if(console == null) {
            System.err.println("No console");
            System.exit(1);
        }

        LocalDate ldInput;
        LocalTime ltZero = LocalTime.of(0, 0, 0);
        OffsetDateTime odtOutput;

        ZoneOffset offset = ZoneId
            .of("Japan").getRules().getOffset(Instant.now());
        DateTimeFormatter format
            = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        String s = console.readLine("%nEnter date: ");
        while(s.length() != 0) {
            // 20170819 : BASIC_ISO_DATE
            // ldInput = LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE); 
            // 2017-12-23 : ISO_LOCAL_DATE
            ldInput = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE); 

            odtOutput = OffsetDateTime.of(ldInput, ltZero, offset);

            // System.out.println(odtOutput);
            // 2010-11-10T00:00+09:00

            System.out.println(odtOutput.format(format));
            // 2018-12-31T00:00:00+09:00

            s = console.readLine("%nEnter date: ");
        }
    }

    static void offsetDateTime()
    {
        LocalDateTime ldtNow = LocalDateTime.now();
        System.out.println(ldtNow);
        // 2017-12-23T06:56:06.527

        ZoneOffset offset;
        // offset = ZoneOffset.of("+09:00");
        offset = ZoneId.of("Japan").getRules().getOffset(Instant.now());
        System.out.println(offset);
        // +09:00 : ZoneOffset

        OffsetDateTime odtNow = OffsetDateTime.of(ldtNow, offset);
        System.out.println(odtNow);
        // 2017-12-23T06:56:06.527+09:00

        OffsetDateTime lastThursday =
            odtNow.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.println(lastThursday);
        // 2017-12-28T07:01:00.606+09:00
    }

    static void zonedDateTime()
    {
        DateTimeFormatter format = DateTimeFormatter
            .ofPattern("yyyy MMM d hh:mm a");

        // Leaving
        LocalDateTime leaving = LocalDateTime
            .of(2017, Month.AUGUST, 31, 19, 30);
        ZoneId leavingZone = ZoneId.of("Japan");  // "Asia/Tokyo", too
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            String s = departure.format(format);
            System.out.printf("LEAVING: %s (%s)%n", s, leavingZone);
            // LEAVING: 2017 Aug 31 07:30 PM (Japan)
        } catch(DateTimeException ex) {
            System.out.println(ex);
        }

        // Flight is 650 minutes
        ZoneId arrivingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime arrival = departure
            .withZoneSameInstant(arrivingZone)
            .plusMinutes(650);

        try {
            String s = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", s, arrivingZone);
        } catch(DateTimeException ex) {
            System.out.println(ex);
        }

        // LEAVING: 2017 Aug 31 07:30 PM (Japan)
        // ARRIVING: 2017 Aug 31 02:20 PM (America/Los_Angeles)

        // see if the daylight saving time
        if(arrivingZone.getRules().isDaylightSavings(arrival.toInstant()))
            System.out.printf("(%s daylight saving time)%n", arrivingZone);
        else
            System.out.printf("(%s standard time)%n", arrivingZone);

        // (America/Los_Angeles daylight saving time)
    }

    static void zoneId()
    {
        LocalDateTime ldtNow = LocalDateTime.now();
        System.out.println(ldtNow);
        // 2017-12-23T05:51:18.430

        ZoneId.getAvailableZoneIds().stream()
            .filter(s -> s.equals("Asia/Tokyo"))
            .peek(e -> System.out.println(e))
            // Asia/Tokyo"
            .map(s -> ZoneId.of(s))
            .peek(e -> System.out.println(e))
            // Asia/Tokyo : ZoneId
            .map(z -> ldtNow.atZone(z))
            .peek(e -> System.out.println(e))
            // 2017-12-23T05:51:18.430+09:00[Asia/Tokyo]
            .map(zdt -> zdt.getOffset())
            .peek(e -> System.out.println(e))
            // +09:00
            .forEach(e -> {});



    }

    static void localTime()
    {
    // The LocalTime deals in time only
    // It does not store time soze or daylight saving time information
        LocalTime ltNow = LocalTime.now();
        System.out.printf("%02d:%02d:%02d%n",
            ltNow.getHour(), ltNow.getMinute(), ltNow.getSecond());
        // 04:42:27 -- but 13:42:27 JST

    // The LocalDateTime handles both date and time
    // To include a time zone, use a ZonedDateTime or an OffsetDateTime
        System.out.println(LocalDateTime.now());
        // 2017-12-23T05:00:31.085

        System.out.println(LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));
        // 1994-04-15T11:30

        System.out.println(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        // 2017-12-23T05:08:07.109

        System.out.println(LocalDateTime.now().plusMonths(6));
        System.out.println(LocalDateTime.now().minusMonths(6));
        // 2018-06-23T05:09:52.431
        // 2017-06-23T05:09:52.431
    }


    static void localDate()
    {
    // A LocalDate represents a year-month-day in the ISO calendar
        LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
        System.out.println(date);
        // 2000-11-20

        date = LocalDate.of(2012, Month.FEBRUARY, 29);
        System.out.println(date);
        System.out.println(date.plusYears(1));
        // 2012-02-29
        // 2013-02-28

    // The YearMonth class represents the month of a specific year
        YearMonth ymNow = YearMonth.now();
        System.out.println(ymNow);
        // 2017-12

        YearMonth ym201002 = YearMonth.of(2010, Month.FEBRUARY);
        System.out.println(ym201002 + ": " + ym201002.lengthOfMonth());
        // 2010-02: 28

        YearMonth ym201202 = YearMonth.of(2012, Month.FEBRUARY);
        System.out.println(ym201202 + ": " + ym201202.lengthOfMonth());
        // 2012-02: 29

    // The MonthDay class represents the day of a particular month
        MonthDay mdDate = MonthDay.of(Month.FEBRUARY, 29);
        System.out.println(mdDate + ": " + mdDate.isValidYear(2010));
        // --02-29: false

    // The Year class represent a year
        System.out.println(Year.of(2012).isLeap());
        // true
    }
}