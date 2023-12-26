package codesmellsjava;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransferRepositoryTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void allReturnsAllTransfersFromStoreMatchingQuery() throws ParseException {
        Account from = new Account(200);
        Account to = new Account();
        TransferStore store = new StubTransferStore();
        TransferRepository repository = new TransferRepository(store);
        List<Transfer> transfers = repository.all(formatter.parse("2023-12-25"), formatter.parse("2023-12-25"), true);
        assertEquals(2, transfers.size());
        assertEquals(new Transfer(100, from, to), transfers.get(0));
        assertEquals(new Transfer(25, from, to), transfers.get(1));
    }

    @Test
    void throwsErrorIfDateRangeInvalid() throws ParseException {
        TransferStore store = new StubTransferStore();
        TransferRepository repository = new TransferRepository(store);
        assertThrows(IllegalArgumentException.class, () -> {
            repository.all(formatter.parse("2023-12-26"), formatter.parse("2023-12-25"), true);
        });
    }

    @Test
    void throwsErrorIfDateRangeInFuture() throws ParseException {
        TransferStore store = new StubTransferStore();
        TransferRepository repository = new TransferRepository(store);
        assertThrows(IllegalArgumentException.class, () -> {
            repository.all(formatter.parse("9999-12-25"), formatter.parse("9999-12-25"), true);
        });
    }
}