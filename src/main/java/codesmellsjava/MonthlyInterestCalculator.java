package codesmellsjava;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

public class MonthlyInterestCalculator {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final double SAVINGS_DAILY_RATE = 0.00015;
    private final double TRANSACTION_DAILY_RATE = 0.0000137;
    private final BankAccount account;
    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;

    public MonthlyInterestCalculator(BankAccount account, BalanceRepository balanceRepository, TransactionRepository transactionRepository) {
        this.account = account;
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
    }

    public int interestForMonth(int year, int month) {
        double interest = 0;
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            try {
                interest += balanceRepository.balance(year, month, day) * (account.accountType() == AccountType.TRANSACTION ?
                        TRANSACTION_DAILY_RATE :
                        (long) transactionRepository.all(
                                formatter.parse("%d-%d-%d".formatted(year, month, 1)),
                                formatter.parse("%d-%d-%d".formatted(year, month, daysInMonth)),
                                false
                        ).size() >= 5 ? SAVINGS_DAILY_RATE : TRANSACTION_DAILY_RATE);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return (int) Math.ceil(interest);
    }
}
