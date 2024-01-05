package codesmellsjava;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public class StubTransactionStore implements Store<Transaction> {
    private final BankAccount from;
    public Date between_from;
    public Date between_to;
    public boolean pending_includePending;

    public StubTransactionStore(BankAccount from) {
        this.from = from;
    }

    @Override
    public Store<Transaction> between(Date from, Date to) {
        between_from = from;
        between_to = to;
        return this;
    }

    @Override
    public Store<Transaction> pending(boolean includePending) {
        pending_includePending = includePending;
        return this;
    }

    @Override
    public List<Transaction> execute() {
        return asList(
                new Transaction(150, from),
                new Transaction(75, from),
                new Transaction(15, from),
                new Transaction(10, from),
                new Transaction(12, from)
        );
    }
}
