package codesmellsjava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

class StatementWithSmallChanges {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final BankAccount account;
    private final TransferRepository transferRepository;
    private final InterestPaymentRepository interestPaymentRepository;

    public StatementWithSmallChanges(BankAccount account, TransferRepository transferRepository, InterestPaymentRepository interestPaymentRepository) {
        this.account = account;
        this.transferRepository = transferRepository;
        this.interestPaymentRepository = interestPaymentRepository;
    }

    private String title(String name, DateRange period) {
        return String.format("%s for account %s - (%s - %s)", name, account.name(), formatter.format(period.from()), formatter.format(period.to()));
    }

    private String transactionReportItems(DateRange period) {
        StringBuilder builder = new StringBuilder();
        for (Transfer transfer : transferRepository.all(period.from(), period.to(), false)) {
            builder.append(String.format("From: %s, To: %s, Amount: %s", transfer.from(), transfer.to(), transfer.amount()));
        }
        return builder.toString();
    }

    private String interestReportItems(DateRange period) {
        StringBuilder builder = new StringBuilder();
        for (InterestPayment payment : interestPaymentRepository.all(period.from(), period.to())) {
            builder.append(String.format("Amount: %s", payment.amount()));
        }
        return builder.toString();
    }

    public String transactionReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(title("Transaction Report", period));
        builder.append(transactionReportItems(period));
        return builder.toString();
    }

    public String interestReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(title("Interest Payment Report", period));
        builder.append(interestReportItems(period));
        return builder.toString();
    }

    public String completeReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(title("Complete Report Report", period));
        builder.append("Interest payments");
        builder.append(transactionReportItems(period));
        builder.append("Transactions");
        builder.append(interestReportItems(period));
        return builder.toString();
    }
}
