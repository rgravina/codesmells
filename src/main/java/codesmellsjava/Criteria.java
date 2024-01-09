package codesmellsjava;

import java.time.YearMonth;

public interface Criteria {
    boolean hasBeenMet(YearMonth currentMonth);
}
