package codesmellsjava;

/*
  There are many other ways a long method can be refactored. It depends on the reason why the method is long.
  This example fixes a long method that contains several steps by making the steps clearer using private methods.
  Some other reason methods might be long include that method does not have a single responsibility, the parameter list
  might be too long, it contains a long or complex if statement, and many more.
 */
public class AccountWithMoreMethods implements BankAccount {
    private boolean isOpen = true;
    private int balance;

    public AccountWithMoreMethods() {
        this.balance = 0;
    }

    public AccountWithMoreMethods(int initialBalance) {
        this.balance = initialBalance;
    }

    public void closeAccount() {
        isOpen = false;
    }

    public int balance() {
        ensureAccountIsOpen();
        logBalanceRequest();
        return customerFacingBalance();
    }

    private void ensureAccountIsOpen() {
        if (!isOpen) {
            throw new AccountNotOpenException();
        }
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
