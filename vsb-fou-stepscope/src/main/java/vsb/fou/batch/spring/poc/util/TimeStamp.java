package vsb.fou.batch.spring.poc.util;


import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class TimeStamp {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendHourOfDay(2)
            .appendLiteral(":")
            .appendMinuteOfHour(2)
            .appendLiteral(":")
            .appendSecondOfMinute(2)
            .appendLiteral(",")
            .appendMillisOfSecond(3)
            .toFormatter();

    public static String getTstamp() {
        return LocalDateTime.now().toString(FORMATTER);
    }
}
