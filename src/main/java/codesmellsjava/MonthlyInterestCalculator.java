package codesmellsjava;

import java.time.YearMonth;

public class MonthlyInterestCalculator {
    private final double SAVINGS_DAILY_RATE = 0.00015;
    private final double TRANSACTION_DAILY_RATE = 0.0000137;
    private final BankAccount account;
    private final BalanceRepository balanceRepository;

    public MonthlyInterestCalculator(BankAccount account, BalanceRepository balanceRepository) {
        this.account = account;
        this.balanceRepository = balanceRepository;
    }

    public int interestForMonth(int year, int month) {
        double interest = 0;
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            interest += balanceRepository.balance(year, month, day) * (account.accountType() == AccountType.TRANSACTION ? TRANSACTION_DAILY_RATE : SAVINGS_DAILY_RATE);
        }
        return (int) Math.ceil(interest);
    }
}
