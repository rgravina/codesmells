package codesmellsjava;

public record Transfer(int amount, BankAccount from, BankAccount to) {
    public Transfer {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
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
