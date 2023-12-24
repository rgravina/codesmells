package codesmellsjava;

public class Transfer {
    private int amount;
    private BankAccount from;
    private BankAccount to;

    public Transfer(int amount, BankAccount from, BankAccount to) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public void send() throws AccountNotOpenException, InsufficientFundsException {
        ensureAccountsAreOpen();
        ensureSufficientFunds();
        performTransfer();
    }

    private void ensureAccountsAreOpen() throws AccountNotOpenException {
        from.ensureAccountIsOpen();
        to.ensureAccountIsOpen();
    }

    private void ensureSufficientFunds() throws InsufficientFundsException, AccountNotOpenException {
        if (from.balance() <= amount) {
            throw new InsufficientFundsException();
        }
    }

    private void performTransfer() {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
