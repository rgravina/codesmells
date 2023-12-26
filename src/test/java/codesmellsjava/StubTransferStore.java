package codesmellsjava;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

class StubTransferStore implements TransferStore {
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
        Account from = new Account(200);
        Account to = new Account();
        return asList(
                new Transfer(100, from, to),
                new Transfer(25, from, to)
        );
    }
}
