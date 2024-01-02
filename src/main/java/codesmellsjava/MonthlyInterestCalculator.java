package codesmellsjava;

public class MonthlyInterestCalculator {
    private final double DAILY_RATE = 0.00015;
    private BankAccount account;
    private final TransferRepository transferRepository;

    public MonthlyInterestCalculator(BankAccount account, TransferRepository transferRepository) {
        this.account = account;
        this.transferRepository = transferRepository;
    }

    public Double interestRateForMonth(int year, int month) throws AccountNotOpenException {
        return Math.ceil(30 * (account.balance() * DAILY_RATE));
    }
}
