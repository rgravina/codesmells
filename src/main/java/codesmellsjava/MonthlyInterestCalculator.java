package codesmellsjava;

import java.time.YearMonth;

public class MonthlyInterestCalculator {
    private final BankAccount account;
    private final BalanceRepository balanceRepository;
    private final DailyInterestCalculator dailyInterestCalculator;

    public MonthlyInterestCalculator(
            BankAccount account,
            BalanceRepository balanceRepository,
            TransactionRepository transactionRepository,
            TransferRepository transferRepository) {
        this.account = account;
        this.balanceRepository = balanceRepository;
        this.dailyInterestCalculator = DailyInterestCalculator.create(
                account,
                balanceRepository,
                transactionRepository,
                transferRepository
        );
    }

    public int interestForMonth(int year, int month) throws AccountNotOpenException {
        double interest = 0;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        int currentBalance = account.balance();

        for (int day = 1; day <= daysInMonth; day++) {
            int balanceOnDay = balanceRepository.balance(year, month, day);
            interest += balanceOnDay * dailyInterestCalculator.dailyInterestRate(yearMonth, currentBalance);
        }

        return (int) Math.ceil(interest);
    }
}
