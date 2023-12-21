package codesmellsjava;

/*
  There are a few steps in this method, but it's not clear where one starts and ends.
  In practice, you may see a lot of code like this. To the person who wrote it at the time, it might look OK.
  But to someone looking at it for the first time, you have to read the whole function to understand what it does.
*/

class Account implements BankAccount {
    private boolean isOpen = true;
    private int balance;

    public Account() {
        this.balance = 0;
    }

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    public void closeAccount() {
        isOpen = false;
    }

    public int balance() {
        if (!isOpen) {
            throw new AccountNotOpenException();
        }

        System.out.println("acc: customer requested account balance");

        if (balance < 0) {
            return 0;
        }

        return balance;
    }
}