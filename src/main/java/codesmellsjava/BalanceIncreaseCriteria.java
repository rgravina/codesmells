package codesmellsjava;

import java.time.YearMonth;

public class BalanceIncreaseCriteria implements Criteria {
    private final BalanceRepository balanceRepository;
    private final int currentBalance;

    public BalanceIncreaseCriteria(BalanceRepository balanceRepository, int currentBalance) {
        this.balanceRepository = balanceRepository;
        this.currentBalance = currentBalance;
    }

    @Override
    public boolean hasBeenMet(YearMonth currentMonth) {
        return balanceRepository.balance(
                currentMonth.minusYears(1).getYear(),
                currentMonth.minusMonths(1).getMonthValue(),
                1) < currentBalance;
    }
}
