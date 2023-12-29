package codesmellsjava;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class StatementTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    void transactionReport() throws ParseException {
        BankAccount from = new Account("test", 20);
        BankAccount to = new Account("test", 30);
        TransferRepository transferRepository = new TransferRepository(new StubTransferStore(from, to));
        InterestPaymentRepository interestPaymentRepository = new InterestPaymentRepository();
        Statement statement = new Statement(from, transferRepository, interestPaymentRepository);
        DateRange range = new DateRange(formatter.parse("2023-12-26"), formatter.parse("2023-12-31"));
        String report = statement.transactionReport(range);
        assertTrue(report.contains("Transaction Report for account test - (2023-12-26 - 2023-12-31)"));
        assertTrue(report.contains("From: test, To: test, Amount: 100"));
        assertTrue(report.contains("From: test, To: test, Amount: 25"));
    }

    @Test
    void interestReport() {
    }

    @Test
    void completeReport() {
    }
}