package codesmellsjava;

public record PointDeposit(int amount) implements FundingSource {
    public static final int POINTS_PER_AUD = 100;

    public int convertToAUD() {
        return amount / POINTS_PER_AUD;
    }

    public boolean canBeFunded() {
        return true;
    }

    public void postDepositProcess() {
        this.deductPointsFromAccount();
    }

    public void deductPointsFromAccount() {
    }
}
