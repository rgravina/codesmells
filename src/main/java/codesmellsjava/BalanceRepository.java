package codesmellsjava;

public class BalanceRepository {
    private final BalanceStore store;

    public BalanceRepository(BalanceStore store) {
        this.store = store;
    }

    public int balance(int year, int month, int day) {
        return this.store.balance(year, month, day);
    }
}
