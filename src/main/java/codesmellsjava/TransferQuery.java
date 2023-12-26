package codesmellsjava;

/*
This class contains the business logic for a transfer query.
Transfers should be in the past.
 */
public record TransferQuery(DateRange range, boolean includePending) {
    boolean isValid() {
        return !range.rangeIsInTheFuture();
    }
}
