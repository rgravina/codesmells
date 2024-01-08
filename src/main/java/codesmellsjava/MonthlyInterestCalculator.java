package codesmellsjava;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

public class MonthlyInterestCalculator {
    public static final int MIN_TRANSACTIONS_FOR_BONUS_INTEREST = 5;
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final double SAVINGS_DAILY_RATE = 0.00015;
    private final double TRANSACTION_DAILY_RATE = 0.0000137;
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

    public int interestForMonth(int year, int month) {
        double interest = 0;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        for (int day = 1; day <= daysInMonth; day++) {
            try {
                DateRange currentMonth = new DateRange(
                        formatter.parse("%d-%d-%d".formatted(year, month, 1)),
                        formatter.parse("%d-%d-%d".formatted(year, month, daysInMonth))
                );

                interest += balanceRepository.balance(year, month, day) * (account.accountType() == AccountType.TRANSACTION ?
                        TRANSACTION_DAILY_RATE :
                        transactionRepository.all(currentMonth, false).size() >= MIN_TRANSACTIONS_FOR_BONUS_INTEREST &&
                                !transferRepository.all(currentMonth, false).isEmpty() &&
                                balanceRepository.balance(
                                        yearMonth.minusYears(1).getYear(),
                                        yearMonth.minusMonths(1).getMonthValue(),
                                        1) < account.balance() ?
                                SAVINGS_DAILY_RATE :
                                TRANSACTION_DAILY_RATE
                );
            } catch (ParseException | AccountNotOpenException e) {
                throw new RuntimeException(e);
            }
        }

        return (int) Math.ceil(interest);
    }
}
