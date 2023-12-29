package codesmellsjava;

/*
  There are many other ways a long method can be refactored. It depends on the reason why the method is long.
  This example fixes a long method that contains several steps by making the steps clearer using private methods.
  Some other reason methods might be long include that method does not have a single responsibility, the parameter list
  might be too long, it contains a long or complex if statement, and many more.
 */
public class AccountWithMoreMethods implements BankAccount {
    private String name;
    private boolean isOpen = true;
    private int balance;

    public AccountWithMoreMethods(String name) {
        this.name = name;
        this.balance = 0;
    }

    public AccountWithMoreMethods(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public void closeAccount() {
        isOpen = false;
    }

    public int balance() throws AccountNotOpenException {
        ensureAccountIsOpen();
        logBalanceRequest();
        return customerFacingBalance();
    }

    public void ensureAccountIsOpen() throws AccountNotOpenException {
        if (!isOpen) {
            throw new AccountNotOpenException();
        }
    }

    public String name() {
        return name;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void transfer(int amount, BankAccount to) throws InsufficientFundsException, AccountNotOpenException, IllegalArgumentException {
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

    private void logBalanceRequest() {
        System.out.println("acc: customer requested account balance");
    }

    private int customerFacingBalance() {
        if (balance < 0) {
            return 0;
        }

        return balance;
    }
}
