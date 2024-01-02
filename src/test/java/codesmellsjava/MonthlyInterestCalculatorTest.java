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

        assertEquals(0, calculator.interestRateForMonth(2023, 12));
    }

    @Test
    void interestWhenMonthHasBalanceOnly() throws AccountNotOpenException {
        Account from = new Account("test", 200);
        Account to = new Account("test");
        StubTransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(from, repository);

        assertEquals(1, calculator.interestRateForMonth(2023, 11));
    }
}