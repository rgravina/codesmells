package codesmellsjava;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

class StubTransferStore implements Store<Transfer> {
    private final BankAccount from;
    private final BankAccount to;
    public Date between_from;
    public Date between_to;
    public boolean pending_includePending;

    public StubTransferStore(BankAccount from, BankAccount to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Store<Transfer> between(Date from, Date to) {
        between_from = from;
        between_to = to;
        return this;
    }

    @Override
    public Store<Transfer> pending(boolean includePending) {
        pending_includePending = includePending;
        return this;
    }

    @Override
    public List<Transfer> execute() {
        return asList(
                new Transfer(100, from, to),
                new Transfer(25, from, to),
                new Transfer(900, from, to)
        );
    }
}
