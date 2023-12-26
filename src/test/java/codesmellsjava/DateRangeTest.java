package codesmellsjava;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateRangeTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void canCreateValidRange() throws ParseException {
        DateRange range = new DateRange(formatter.parse("2023-12-26"), formatter.parse("2023-12-31"));
        assertEquals(range.from(), formatter.parse("2023-12-26"));
        assertEquals(range.to(), formatter.parse("2023-12-31"));
    }

    @Test
    void invalidRangeThrowsException() throws ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            DateRange range = new DateRange(formatter.parse("2023-12-26"), formatter.parse("2023-12-25"));
        });
    }
}
