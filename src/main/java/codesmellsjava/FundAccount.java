package codesmellsjava;

public class FundAccount {
    void func(int amount, BankAccount account, FundingSource source) throws FundingSourceRejectedException {
        if (!source.canBeFunded()) {
            throw new FundingSourceRejectedException();
        }
        int amountToDeposit = source.convertToAUD();
        account.deposit(amountToDeposit);
        source.postDepositProcess();
    }
}
