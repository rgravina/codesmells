package codesmellsjava;

import java.time.YearMonth;

import static java.util.Arrays.asList;

public class SavingsAccountDailyInterestCalculator implements DailyInterestCalculator {
     private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;

    public SavingsAccountDailyInterestCalculator(
            BalanceRepository balanceRepository,
            TransactionRepository transactionRepository,
            TransferRepository transferRepository) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
    }

    public double dailyInterestRate(YearMonth yearMonth, int currentBalance) {
        return hasMetBonusInterestRequirements(yearMonth, currentBalance) ?
                InterestDailyRate.SAVINGS_DAILY_RATE :
                InterestDailyRate.TRANSACTION_DAILY_RATE;
    }

    private boolean hasMetBonusInterestRequirements(YearMonth yearMonth, int currentBalance) {
        return new BonusInterestCriteria(
                asList(
                        new MinimumTransactionsCriteria(transactionRepository),
                        new DepositCriteria(transferRepository),
                        new BalanceIncreaseCriteria(balanceRepository, currentBalance)
                )
        ).hasBeenMet(yearMonth);
    }
}
