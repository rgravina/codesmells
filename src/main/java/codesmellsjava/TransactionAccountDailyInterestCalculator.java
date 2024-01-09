package codesmellsjava;

import java.time.YearMonth;

public class TransactionAccountDailyInterestCalculator implements DailyInterestCalculator {
    public double dailyInterestRate(YearMonth yearMonth, int currentBalance) {
        return InterestDailyRate.TRANSACTION_DAILY_RATE;
    }
}
