package codesmellsjava;

import java.time.YearMonth;
import java.util.List;

public class BonusInterestCriteria {
    private final List<Criteria> criteria;

    public BonusInterestCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    public boolean hasBeenMet(YearMonth currentMonth) {
        return criteria.stream().allMatch(it -> it.hasBeenMet(currentMonth));
    }
}
