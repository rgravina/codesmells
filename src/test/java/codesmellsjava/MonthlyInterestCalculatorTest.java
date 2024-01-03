package codesmellsjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyInterestCalculatorTest {
    @Test
    void interestWhenMonthHasNoBalanceOrTransactions() throws AccountNotOpenException {
        Account from = new Account("test");
        Account to = new Account("test");
        StubTransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(from, repository);

        assertEquals(0, calculator.interestForMonth(2023, 12));
    }

    @Test
    void interestWhenMonthHasBalanceOnly() throws AccountNotOpenException {
        Account from = new Account("test", 50000);
        Account to = new Account("test");
        StubTransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(from, repository);

        assertEquals(225, calculator.interestForMonth(2023, 11));
        assertEquals(233, calculator.interestForMonth(2023, 12));
    }
}