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
    Account from = new Account("test", 200);
    Account to = new Account("test");

    @Test
    void allReturnsAllTransfersFromStoreMatchingQuery() throws ParseException {
        StubTransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);

        List<Transfer> transfers = repository.all(
                formatter.parse("2023-12-25"),
                formatter.parse("2023-12-26"),
                true
        );

        assertEquals(2, transfers.size());
        assertEquals(formatter.parse("2023-12-25"), store.between_from);
        assertEquals(formatter.parse("2023-12-26"), store.between_to);
        assertEquals(new Transfer(100, from, to), transfers.get(0));
        assertEquals(new Transfer(25, from, to), transfers.get(1));
    }

    @Test
    void throwsErrorIfDateRangeInvalid() {
        TransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.all(formatter.parse("2023-12-26"), formatter.parse("2023-12-25"), true);
        });
    }

    @Test
    void throwsErrorIfDateRangeInFuture() {
        TransferStore store = new StubTransferStore(from, to);
        TransferRepository repository = new TransferRepository(store);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.all(formatter.parse("9999-12-25"), formatter.parse("9999-12-25"), true);
        });
    }
}