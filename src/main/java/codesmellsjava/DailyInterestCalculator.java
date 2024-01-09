package codesmellsjava;

import java.time.YearMonth;

interface DailyInterestCalculator {
    double dailyInterestRate(YearMonth yearMonth, int currentBalance);
}
