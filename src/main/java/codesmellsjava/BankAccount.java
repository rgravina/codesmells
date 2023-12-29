package codesmellsjava;

public interface BankAccount {
    void closeAccount();

    void ensureAccountIsOpen() throws AccountNotOpenException;

    String name();

    int balance() throws AccountNotOpenException;

    void withdraw(int amount);

    void deposit(int amount);

    void transfer(int amount, BankAccount to) throws InsufficientFundsException, AccountNotOpenException, IllegalArgumentException;
}
