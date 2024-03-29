package codesmellsjava;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyInterestCalculatorTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private StubBalanceStore balanceStore;
    private StubTransactionStore transactionStore;
    private MonthlyInterestCalculator calculator;

    void setUp(AccountType accountType) {
        Account account = new Account("test", accountType, 50000);
        balanceStore = new StubBalanceStore();
        BalanceRepository balanceRepository = new BalanceRepository(balanceStore);
        transactionStore = new StubTransactionStore(account);
        TransactionRepository transactionRepository = new TransactionRepository(transactionStore);
        Account from = new Account("test", AccountType.TRANSACTION, 50000);
        Store<Transfer> transferStore = new StubTransferStore(from, account);
        TransferRepository transferRepository = new TransferRepository(transferStore);
        DailyInterestCalculator interestCalculator = DailyInterestCalculator.create(
                account,
                balanceRepository,
                transactionRepository,
                transferRepository
        );
        calculator = new MonthlyInterestCalculator(account, balanceRepository, interestCalculator);
    }

    @Test
    void interestWithMonthsOfDifferentLengthAndBalancesWithBonusInterestGoalsMet() throws ParseException, AccountNotOpenException {
        setUp(AccountType.SAVINGS);
        assertEquals(136, calculator.interestForMonth(2023, 11));
        assertEquals(160, calculator.interestForMonth(2023, 12));
        assertEquals(formatter.parse("%d-%d-%d".formatted(2023, 12, 1)), transactionStore.between_from);
        assertEquals(formatter.parse("%d-%d-%d".formatted(2023, 12, 31)), transactionStore.between_to);
        assertFalse(transactionStore.pending_includePending);
    }

    @Test
    void interestWithSavingsAccountWithBonusInterestGoalsMet() throws AccountNotOpenException {
        setUp(AccountType.SAVINGS);
        assertEquals(160, calculator.interestForMonth(2023, 12));
    }

    @Test
    void interestWithTransactionAccountWithBonusInterestGoalsMet() throws AccountNotOpenException {
        setUp(AccountType.TRANSACTION);
        assertEquals(15, calculator.interestForMonth(2023, 12));
    }

    @Test
    void interestWithSavingsAccountForJanuary() throws AccountNotOpenException {
        setUp(AccountType.SAVINGS);
        assertEquals(94, calculator.interestForMonth(2023, 1));
        assertEquals(2022, balanceStore.balance_year);
        assertEquals(12, balanceStore.balance_month);
    }
}