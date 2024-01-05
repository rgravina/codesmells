package codesmellsjava;

import java.util.Date;
import java.util.List;

public class InMemoryTransferStore implements Store<Transfer> {
    @Override
    public Store<Transfer> between(Date from, Date to) {
        return this;
    }

    @Override
    public Store<Transfer> pending(boolean includePending) {
        return this;
    }

    @Override
    public List<Transfer> execute() {
        return null;
    }
}
