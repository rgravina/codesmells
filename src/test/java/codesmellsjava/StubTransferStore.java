package codesmellsjava;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

class StubTransferStore implements TransferStore {
    private final Account from;
    private final Account to;
    public Date between_from;
    public Date between_to;
    public boolean pending_includePending;

    public StubTransferStore(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public TransferStore between(Date from, Date to) {
        between_from = from;
        between_to = to;
        return this;
    }

    @Override
    public TransferStore pending(boolean includePending) {
        pending_includePending = includePending;
        return this;
    }

    @Override
    public List<Transfer> execute() {
        return asList(
                new Transfer(100, from, to),
                new Transfer(25, from, to)
        );
    }
}
