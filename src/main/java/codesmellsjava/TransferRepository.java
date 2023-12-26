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
    private final TransferStore store;

    public TransferRepository(TransferStore store) {
        this.store = store;
    }

    public List<Transfer> all(Date from, Date to, boolean includePending) {
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
