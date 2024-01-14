package codesmellsjava;

public record CashDeposit(int amount) implements FundingSource {
    public void storeInVault(BranchLocation branchLocation) {
    }

    public boolean canBeFunded() {
        return true;
    }

    public int convertToAUD() {
        return amount();
    }

    public void postDepositProcess() {
        storeInVault(BranchLocation.MELBOURNE);
    }
}
