package codesmellsjava;

public interface FundingSource {
    boolean canBeFunded();

    int convertToAUD();

    void postDepositProcess();
}
