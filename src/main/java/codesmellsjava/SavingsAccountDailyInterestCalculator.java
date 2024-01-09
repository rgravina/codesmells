package codesmellsjava;

import java.time.YearMonth;

public class SavingsAccountDailyInterestCalculator implements DailyInterestCalculator {
    public static final int MIN_TRANSACTIONS_FOR_BONUS_INTEREST = 5;
    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;

    public SavingsAccountDailyInterestCalculator(
            BalanceRepository balanceRepository,
            TransactionRepository transactionRepository,
            TransferRepository transferRepository) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
    }

    public double dailyInterestRate(YearMonth yearMonth, int currentBalance) {
        return hasMetBonusInterestRequirements(yearMonth, currentBalance) ?
                InterestDailyRate.SAVINGS_DAILY_RATE :
                InterestDailyRate.TRANSACTION_DAILY_RATE;
    }

    private boolean hasMetBonusInterestRequirements(YearMonth yearMonth, int currentBalance) {
        DateRange currentMonth = DateRange.rangeForMonth(yearMonth.getYear(), yearMonth.getMonthValue());
        boolean hasMinimumNumberOfTransactionsThisMonth = transactionRepository.all(currentMonth, false).size() >= MIN_TRANSACTIONS_FOR_BONUS_INTEREST;
        boolean hasHadMoneyDeposited = !transferRepository.all(currentMonth, false).isEmpty();
        boolean balanceIsHigherThanEndOfPreviousMonth = balanceRepository.balance(
                yearMonth.minusYears(1).getYear(),
                yearMonth.minusMonths(1).getMonthValue(),
                1) < currentBalance;

        return hasMinimumNumberOfTransactionsThisMonth &&
                hasHadMoneyDeposited &&
                balanceIsHigherThanEndOfPreviousMonth;
    }
}
