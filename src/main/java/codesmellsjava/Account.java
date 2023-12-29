package codesmellsjava;

/*
  There are a few steps in this method, but it's not clear where one starts and ends.
  In practice, you may see a lot of code like this. To the person who wrote it at the time, it might look OK.
  But to someone looking at it for the first time, you have to read the whole function to understand what it does.
*/

import java.util.Objects;

class Account implements BankAccount {
    private String name;
    private boolean isOpen = true;
    private int balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public Account(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public void closeAccount() {
        isOpen = false;
    }

    public void ensureAccountIsOpen() throws AccountNotOpenException {
        if (!isOpen) {
            throw new AccountNotOpenException();
        }
    }

    public String name() {
        return name;
    }

    public int balance() throws AccountNotOpenException {
        if (!isOpen) {
            throw new AccountNotOpenException();
        }

        System.out.println("acc: customer requested account balance");

        if (balance < 0) {
            return 0;
        }

        return balance;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void transfer(int amount, BankAccount to) throws InsufficientFundsException, AccountNotOpenException {
        this.ensureAccountIsOpen();
        to.ensureAccountIsOpen();

        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        if (this.balance <= amount) {
            throw new InsufficientFundsException();
        }

        this.withdraw(amount);
        to.deposit(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return isOpen == account.isOpen && balance == account.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen, balance);
    }
}