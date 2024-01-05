package codesmellsjava;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class TransactionRepository {
    private final Store<Transaction> store;

    public TransactionRepository(Store<Transaction> store) {
        this.store = store;
    }

    public List<Transaction> all(Date from, Date to, boolean includePending) {
        if (from.after(to)) {
            throw new IllegalArgumentException();
        }

        if (from.after(Date.from(Instant.now()))) {
            throw new IllegalArgumentException();
        }

        return store
                .between(from, to)
                .pending(includePending)
                .execute();
    }
}
