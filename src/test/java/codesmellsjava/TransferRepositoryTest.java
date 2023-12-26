package codesmellsjava;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferRepositoryTest {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    @Disabled
    void allReturnsAllTransfersFromStoreMatchingQuery() throws ParseException {
        InMemoryTransferStore store = new InMemoryTransferStore();
        TransferRepository repository = new TransferRepository(store);
        List<Transfer> transfers = repository.all(formatter.parse("2023-12-25"), formatter.parse("2023-12-25"), true);
    }
}