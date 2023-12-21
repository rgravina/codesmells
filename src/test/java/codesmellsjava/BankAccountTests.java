package codesmellsjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class BankAccountTests {
    public abstract BankAccount createBankAccount();

    public abstract BankAccount createBankAccountWithBalance(int balance);

    BankAccount account;

    @Test
    void returnsBalanceWhenZero() {
        account = createBankAccount();
        assertEquals(0, account.balance());
    }

    @Test
    void returnsBalanceIfInCredit() {
        account = createBankAccountWithBalance(100);
        assertEquals(100, account.balance());
    }

    @Test
    void returnsZeroBalanceIfInDebt() {
        account = createBankAccountWithBalance(-100);
        assertEquals(0, account.balance());
    }

    @Test()
    void throwsExceptionIfAccountClosed() {
        account = createBankAccountWithBalance(100);
        account.closeAccount();
        assertThrows(AccountNotOpenException.class, account::balance);
    }
}

class AccountTest extends BankAccountTests {
    @Override
    public BankAccount createBankAccount() {
        return new Account();
    }

    @Override
    public BankAccount createBankAccountWithBalance(int balance) {
        return new Account(balance);
    }
}

class AccountWithMoreMethodsTest extends BankAccountTests {
    @Override
    public BankAccount createBankAccount() {
        return new AccountWithMoreMethods();
    }

    @Override
    public BankAccount createBankAccountWithBalance(int balance) {
        return new AccountWithMoreMethods(balance);
    }
}