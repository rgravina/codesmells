package codesmellsjava;

import java.time.YearMonth;

public interface DailyInterestCalculator {
    static DailyInterestCalculator create(BankAccount account,
                                          BalanceRepository balanceRepository,
                                          TransactionRepository transactionRepository,
                                          TransferRepository transferRepository) {
        return switch (account.accountType()) {
            case TRANSACTION -> new TransactionAccountDailyInterestCalculator();
            case SAVINGS -> new SavingsAccountDailyInterestCalculator(
                    balanceRepository, transactionRepository, transferRepository
            );
            case INVESTMENT -> new InvestmentAccountDailyInterestCalculator(
                    transactionRepository, transferRepository
            );
        };
    }

    double dailyInterestRate(YearMonth yearMonth, int currentBalance);
}
