package codesmellsjava;

import java.time.YearMonth;

public class MonthlyInterestCalculator {
    private final double DAILY_RATE = 0.00015;
    private final BalanceRepository balanceRepository;

    public MonthlyInterestCalculator(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public int interestForMonth(int year, int month) {
        double interest = 0;
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            interest += balanceRepository.balance(year, month, day) * DAILY_RATE;
        }
        return (int) Math.ceil(interest);
    }
}
