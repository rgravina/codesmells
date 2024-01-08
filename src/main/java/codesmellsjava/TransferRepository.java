package codesmellsjava;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/*
This query method seems OK at first glance. But there a some ways it can be improved.
The parameters all belong together as they are all part of querying for a list of transfers.
Date ranges are two values that only have meaning together so it makes sense to move them to another class.
The date checking logic, is there a better place for it?
*/

public class TransferRepository {
    private final Store<Transfer> store;

    public TransferRepository(Store<Transfer> store) {
        this.store = store;
    }

    public List<Transfer> all(DateRange range, boolean includePending) {
        if (range.rangeIsInTheFuture()) {
            throw new IllegalArgumentException();
        }

        return store
                .between(range.from(), range.to())
                .pending(includePending)
                .execute();
    }
}
