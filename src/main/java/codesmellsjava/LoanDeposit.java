package codesmellsjava;

public record LoanDeposit(int amount) implements FundingSource {
    public void startLoan() {
    }

    public boolean creditCheck() {
        return true;
    }

    public boolean canBeFunded() {
        return creditCheck();
    }

    @Override
    public int convertToAUD() {
        return amount;
    }

    @Override
    public void postDepositProcess() {
        startLoan();
    }
}
