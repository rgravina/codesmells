package codesmellsjava;

import java.time.YearMonth;

public class MinimumTransactionsCriteria implements Criteria {
    public static final int MIN_TRANSACTIONS_FOR_BONUS_INTEREST = 5;
    private final TransactionRepository transactionRepository;

    public MinimumTransactionsCriteria(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean hasBeenMet(YearMonth currentMonth) {
        return transactionRepository.all(
                DateRange.rangeForMonth(currentMonth.getYear(), currentMonth.getMonthValue()),
                false).size() >= MIN_TRANSACTIONS_FOR_BONUS_INTEREST;
    }
}
