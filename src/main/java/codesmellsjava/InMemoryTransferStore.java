package codesmellsjava;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class InMemoryTransferStore implements TransferStore {
    @Override
    public TransferStore between(Date from, Date to) {
        return this;
    }

    @Override
    public TransferStore pending(boolean includePending) {
        return this;
    }

    @Override
    public List<Transfer> execute() {
        return null;
    }
}
