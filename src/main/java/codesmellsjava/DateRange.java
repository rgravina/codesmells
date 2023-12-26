package codesmellsjava;

import java.time.Instant;
import java.util.Date;

/*
This class neatly encapsulates a date range and asking questions about the range.
 */
public record DateRange(Date from, Date to) {
    public DateRange {
        if(from.after(to)) {
            throw new IllegalArgumentException();
        }
    }
    boolean rangeIsInTheFuture() {
        return from.after(Date.from(Instant.now()));
    }
}
