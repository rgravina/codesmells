package codesmellsjava;

import java.time.YearMonth;

public class MonthlyInterestCalculator {
    public static final int MIN_TRANSACTIONS_FOR_BONUS_INTEREST = 5;
    private static final double SAVINGS_DAILY_RATE = (5.5 / 100) / 365;
    private static final double TRANSACTION_DAILY_RATE = (0.5 / 100) / 365;
    private final BankAccount account;
    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;

    public MonthlyInterestCalculator(
            BankAccount account,
            BalanceRepository balanceRepository,
            TransactionRepository transactionRepository,
            TransferRepository transferRepository) {
        this.account = account;
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
    }

    public int interestForMonth(int year, int month) throws AccountNotOpenException {
        double interest = 0;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        int currentBalance = account.balance();

        for (int day = 1; day <= daysInMonth; day++) {
            int balanceOnDay = balanceRepository.balance(year, month, day);
            interest += balanceOnDay * dailyInterestRate(yearMonth, currentBalance);
        }

        return (int) Math.ceil(interest);
    }

    private double dailyInterestRate(YearMonth yearMonth, int currentBalance) {
        return account.accountType() == AccountType.TRANSACTION ?
                TRANSACTION_DAILY_RATE :
                hasMetBonusInterestRequirements(yearMonth, currentBalance) ?
                        SAVINGS_DAILY_RATE :
                        TRANSACTION_DAILY_RATE;
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
