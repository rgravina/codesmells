package codesmellsjava;

import java.util.List;

public class TransactionRepository {
    private final Store<Transaction> store;

    public TransactionRepository(Store<Transaction> store) {
        this.store = store;
    }

    public List<Transaction> all(DateRange range, boolean includePending) {
        if (range.rangeIsInTheFuture()) {
            throw new IllegalArgumentException();
        }

        return store
                .between(range.from(), range.to())
                .pending(includePending)
                .execute();
    }
}
