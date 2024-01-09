package codesmellsjava;

import java.time.YearMonth;

import static java.util.Arrays.asList;

public class InvestmentAccountDailyInterestCalculator implements DailyInterestCalculator {
    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;

    public InvestmentAccountDailyInterestCalculator(
            TransactionRepository transactionRepository,
            TransferRepository transferRepository) {
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
    }

    public double dailyInterestRate(YearMonth yearMonth, int currentBalance) {
        return hasMetBonusInterestRequirements(yearMonth) ?
                InterestDailyRate.INVESTMENT_DAILY_RATE :
                InterestDailyRate.TRANSACTION_DAILY_RATE;
    }

    private boolean hasMetBonusInterestRequirements(YearMonth yearMonth) {
        return new BonusInterestCriteria(
                asList(
                        new MinimumTransactionsCriteria(transactionRepository),
                        new DepositCriteria(transferRepository)
                )
        ).hasBeenMet(yearMonth);
    }
}
