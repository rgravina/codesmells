package codesmellsjava;

import java.time.YearMonth;

public class DepositCriteria implements Criteria {
    private final TransferRepository transferRepository;

    public DepositCriteria(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public boolean hasBeenMet(YearMonth currentMonth) {
        return !transferRepository.all(
                        DateRange.rangeForMonth(currentMonth.getYear(), currentMonth.getMonthValue()),
                        false)
                .isEmpty();
    }
}
