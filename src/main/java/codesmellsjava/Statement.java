package codesmellsjava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Statement {
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final BankAccount account;
    private final TransferRepository transferRepository;
    private final InterestPaymentRepository interestPaymentRepository;

    public Statement(BankAccount account, TransferRepository transferRepository, InterestPaymentRepository interestPaymentRepository) {
        this.account = account;
        this.transferRepository = transferRepository;
        this.interestPaymentRepository = interestPaymentRepository;
    }

    public String transactionReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Transaction Report for account %s - (%s - %s)", account.name(), formatter.format(period.from()), formatter.format(period.to())));
        for (Transfer transfer: transferRepository.all(period, false)) {
            builder.append(String.format("From: %s, To: %s, Amount: %s", transfer.from(), transfer.to(), transfer.amount()));
        }
        return builder.toString();
    }

    public String interestReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Interest Payment Report for account %s - (%s - %s)", account.name(), formatter.format(period.from()), formatter.format(period.to())));
        for (InterestPayment payment: interestPaymentRepository.all(period.from(), period.to())) {
            builder.append(String.format("Amount: %s", payment.amount()));
        }
        return builder.toString();
    }

    public String completeReport(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Complete Report for account %s - (%s - %s)", account.name(), formatter.format(period.from()), formatter.format(period.to())));
        builder.append("Interest payments");
        for (InterestPayment payment: interestPaymentRepository.all(period.from(), period.to())) {
            builder.append(String.format("Amount: %s", payment.amount()));
        }
        builder.append("Transactions");
        for (Transfer transfer: transferRepository.all(period, false)) {
            builder.append(String.format("From: %s, To: %s, Amount: %s", transfer.from(), transfer.to(), transfer.amount()));
        }
        return builder.toString();
    }
}
