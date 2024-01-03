package codesmellsjava;

public class StubBalanceStore implements BalanceStore {
    public int balance_year;
    public int balance_month;
    public int balance_day;

    @Override
    public int balance(int year, int month, int day) {
        this.balance_year = year;
        this.balance_month = month;
        this.balance_day = day;
        if (year == 2023 && month == 10) {
            return 0;
        }
        if (year == 2023 && month == 11) {
            return 50000;
        }
        if (year == 2023 && month == 12 && day < 15) {
            return 40000;
        }
        if (year == 2023 && month == 12 && day < 31) {
            return 30000;
        }
        return 20000;
    }
}
