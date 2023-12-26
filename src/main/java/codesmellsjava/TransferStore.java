package codesmellsjava;

import java.util.Date;
import java.util.List;

public interface TransferStore {
    TransferStore between(Date from, Date to);

    TransferStore pending(boolean includePending);

    List<Transfer> execute();
}
