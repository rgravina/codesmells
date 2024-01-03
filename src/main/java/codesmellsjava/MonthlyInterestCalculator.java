package codesmellsjava;

import java.time.YearMonth;

public class MonthlyInterestCalculator {
    private final double DAILY_RATE = 0.00015;
    private BankAccount account;
    private final TransferRepository transferRepository;

    public MonthlyInterestCalculator(BankAccount account, TransferRepository transferRepository) {
        this.account = account;
        this.transferRepository = transferRepository;
    }

    public int interestForMonth(int year, int month) throws AccountNotOpenException {
        double interest = 0;
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            interest += account.balance() * DAILY_RATE;
        }
        return (int) Math.ceil(interest);
    }
}
