package codesmellsjava;

import java.util.List;

public class TransferRepositoryWithParameterObject {
    private TransferStore store;

    public TransferRepositoryWithParameterObject(TransferStore store) {
        this.store = store;
    }

    public List<Transfer> all(TransferQuery query) {
        if (!query.isValid()) {
            throw new IllegalArgumentException();
        }

        return store
                .between(query.range().from(), query.range().to())
                .pending(query.includePending())
                .execute();
    }
}
