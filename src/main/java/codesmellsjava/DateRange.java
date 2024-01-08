package codesmellsjava;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.YearMonth;
import java.util.Date;

/*
This class neatly encapsulates a date range and asking questions about the range.
 */
public record DateRange(Date from, Date to) {
    static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public DateRange {
        if (from.after(to)) {
            throw new IllegalArgumentException();
        }
    }

    public static DateRange rangeForMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        try {
            return new DateRange(
                    formatter.parse("%d-%d-%d".formatted(year, month, 1)),
                    formatter.parse("%d-%d-%d".formatted(year, month, daysInMonth))
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    boolean rangeIsInTheFuture() {
        return from.after(Date.from(Instant.now()));
    }
}
