package codesmellsjava;

import java.util.Objects;

public class Transfer {
    private final int amount;
    private final BankAccount from;
    private final BankAccount to;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return amount == transfer.amount && Objects.equals(from, transfer.from) && Objects.equals(to, transfer.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, from, to);
    }
}
