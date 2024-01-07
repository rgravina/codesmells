package codesmellsjava;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyInterestCalculatorTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void interestWithMonthsOfDifferentLengthAndBalancesWithBonusInterestGoalsMet() throws ParseException {
        Account account = new Account("test", AccountType.SAVINGS, 50000);
        StubBalanceStore balanceStore = new StubBalanceStore();
        BalanceRepository balanceRepository = new BalanceRepository(balanceStore);
        StubTransactionStore transactionStore = new StubTransactionStore(account);
        TransactionRepository transactionRepository = new TransactionRepository(transactionStore);
        Account from = new Account("test", AccountType.TRANSACTION, 50000);
        Store<Transfer> transferStore = new StubTransferStore(from, account);
        TransferRepository transferRepository = new TransferRepository(transferStore);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, balanceRepository, transactionRepository, transferRepository);

        assertEquals(225, calculator.interestForMonth(2023, 11));
        assertEquals(159, calculator.interestForMonth(2023, 12));
        assertEquals(2023, balanceStore.balance_year);
        assertEquals(12, balanceStore.balance_month);
        assertEquals(31, balanceStore.balance_day);
        assertEquals(formatter.parse("%d-%d-%d".formatted(2023, 12, 1)), transactionStore.between_from);
        assertEquals(formatter.parse("%d-%d-%d".formatted(2023, 12, 31)), transactionStore.between_to);
        assertFalse(transactionStore.pending_includePending);
    }

    @Test
    void interestWithSavingsAccountWithBonusInterestGoalsMet() throws AccountNotOpenException {
        Account account = new Account("test", AccountType.SAVINGS, 50000);
        StubBalanceStore balanceStore = new StubBalanceStore();
        BalanceRepository balanceRepository = new BalanceRepository(balanceStore);
        StubTransactionStore transactionStore = new StubTransactionStore(account);
        TransactionRepository transactionRepository = new TransactionRepository(transactionStore);
        Account from = new Account("test", AccountType.TRANSACTION, 50000);
        Store<Transfer> transferStore = new StubTransferStore(from, account);
        TransferRepository transferRepository = new TransferRepository(transferStore);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, balanceRepository, transactionRepository, transferRepository);

        assertEquals(159, calculator.interestForMonth(2023, 12));
    }

    @Test
    void interestWithTransactionAccountWithBonusInterestGoalsMet() throws AccountNotOpenException {
        Account account = new Account("test", AccountType.TRANSACTION, 50000);
        StubBalanceStore balanceStore = new StubBalanceStore();
        BalanceRepository balanceRepository = new BalanceRepository(balanceStore);
        StubTransactionStore transactionStore = new StubTransactionStore(account);
        TransactionRepository transactionRepository = new TransactionRepository(transactionStore);
        Account from = new Account("test", AccountType.TRANSACTION, 50000);
        Store<Transfer> transferStore = new StubTransferStore(from, account);
        TransferRepository transferRepository = new TransferRepository(transferStore);
        MonthlyInterestCalculator calculator = new MonthlyInterestCalculator(account, balanceRepository, transactionRepository, transferRepository);

        assertEquals(15, calculator.interestForMonth(2023, 12));
    }
}