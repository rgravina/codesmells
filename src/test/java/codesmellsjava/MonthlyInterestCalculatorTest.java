package codesmellsjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyInterestCalculatorTest {
    @Test
    void interestWithMonthsOfDifferentLengthAndBalances() throws AccountNotOpenException {
        Account account = new Account("test",  AccountType.SAVINGS, 50000);
        StubBalanceStore store = new StubBalanceStore();
        BalanceRepository repository = new BalanceRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, repository);

        assertEquals(225, calculator.interestForMonth(2023, 11));
        assertEquals(159, calculator.interestForMonth(2023, 12));
        assertEquals(2023, store.balance_year);
        assertEquals(12, store.balance_month);
        assertEquals(31, store.balance_day);
    }

    @Test
    void interestWithSavingsAccount() throws AccountNotOpenException {
        Account account = new Account("test",  AccountType.SAVINGS, 50000);
        StubBalanceStore store = new StubBalanceStore();
        BalanceRepository repository = new BalanceRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, repository);

        assertEquals(159, calculator.interestForMonth(2023, 12));
    }

    @Test
    void interestWithTransactionAccount() throws AccountNotOpenException {
        Account account = new Account("test",  AccountType.TRANSACTION, 50000);
        StubBalanceStore store = new StubBalanceStore();
        BalanceRepository repository = new BalanceRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, repository);

        assertEquals(15, calculator.interestForMonth(2023, 12));
    }
}