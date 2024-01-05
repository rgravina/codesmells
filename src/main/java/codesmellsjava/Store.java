package codesmellsjava;

import java.util.Date;
import java.util.List;

public interface Store<T> {
    Store<T> between(Date from, Date to);

    Store<T> pending(boolean includePending);

    List<T> execute();
}
