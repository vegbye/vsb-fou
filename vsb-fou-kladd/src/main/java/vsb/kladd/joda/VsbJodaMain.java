package vsb.kladd.joda;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

public class VsbJodaMain {

    private static final Logger log = Logger.getLogger(VsbJodaMain.class);

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        log.info(dateTime);
        int month = dateTime.getMonthOfYear();
        log.info(month);

        DateTime start = new DateTime(2004, 1, 1, 0, 0, 0, 0);
        DateTime end = new DateTime(2006, 1, 4, 0, 0, 0, 0);
        DateTime aprilsnarr = new DateTime().withDate(2006, 4, 1);

        DateTimeZone.getDefault();

        Period p = new Period(start, end);
        log.info(p);
        log.info(p.toString(ISOPeriodFormat.alternateExtended()));
    }
}
