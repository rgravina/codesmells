package codesmellsjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class BankAccountTests {
    public abstract BankAccount createBankAccount();

    public abstract BankAccount createBankAccountWithBalance(int balance);

    BankAccount account;

    @Test
    void returnsBalanceWhenZero() throws AccountNotOpenException {
        account = createBankAccount();
        assertEquals(0, account.balance());
    }

    @Test
    void returnsBalanceIfInCredit() throws AccountNotOpenException {
        account = createBankAccountWithBalance(100);
        assertEquals(100, account.balance());
    }

    @Test
    void returnsZeroBalanceIfInDebt() throws AccountNotOpenException {
        account = createBankAccountWithBalance(-100);
        assertEquals(0, account.balance());
    }

    @Test()
    void throwsExceptionIfAccountClosed() {
        account = createBankAccountWithBalance(100);
        account.closeAccount();
        assertThrows(AccountNotOpenException.class, account::balance);
    }

    @Test
    void transferSendsMoney() throws AccountNotOpenException, InsufficientFundsException {
        account = createBankAccountWithBalance(100);
        BankAccount to = createBankAccountWithBalance(50);
        account.transfer(15, to);
        assertEquals(85, account.balance());
        assertEquals(65, to.balance());
    }
}

class AccountTest extends BankAccountTests {
    @Override
    public BankAccount createBankAccount() {
        return new Account("test");
    }

    @Override
    public BankAccount createBankAccountWithBalance(int balance) {
        return new Account("test", AccountType.TRANSACTION, balance);
    }
}

class AccountWithMoreMethodsTest extends BankAccountTests {
    @Override
    public BankAccount createBankAccount() {
        return new AccountWithMoreMethods("test");
    }

    @Override
    public BankAccount createBankAccountWithBalance(int balance) {
        return new AccountWithMoreMethods("test", AccountType.TRANSACTION, balance);
    }
}