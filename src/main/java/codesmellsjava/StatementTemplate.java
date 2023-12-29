package codesmellsjava;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

abstract class Report {
    private final BankAccount account;

    Report(BankAccount account) {
        this.account = account;
    }

    abstract String name();

    abstract List<String> items(DateRange period);

    public String title(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s for account %s - (%s - %s)", this.name(), account.name(), period.from(), period.to()));
        return builder.toString();
    }

    public String report(DateRange period) {
        StringBuilder builder = new StringBuilder();
        builder.append(title(period));
        builder.append(items(period));
        return builder.toString();
    }

}

class TransactionReport extends Report {
    private final TransferRepository transferRepository;

    public TransactionReport(BankAccount account, TransferRepository transferRepository) {
        super(account);
        this.transferRepository = transferRepository;
    }

    String name() {
        return "Transaction Report";
    }

    List<String> items(DateRange period) {
        return transferRepository.all(period.from(), period.to(), false)
                .stream()
                .map(transfer -> String.format("From: %s, To: %s, Amount: %s", transfer.from(), transfer.to(), transfer.amount()))
                .collect(toList());
    }
}

class InterestReport extends Report {
    private final InterestPaymentRepository interestPaymentRepository;

    public InterestReport(BankAccount account, InterestPaymentRepository interestPaymentRepository) {
        super(account);
        this.interestPaymentRepository = interestPaymentRepository;
    }

    String name() {
        return "Interest Payment Report";
    }

    List<String> items(DateRange period) {
        return interestPaymentRepository.all(period.from(), period.to())
                .stream()
                .map(payment -> String.format("Amount: %s", payment.amount()))
                .collect(toList());
    }
}

class CompleteReport extends Report {
    private final TransactionReport transactionReport;
    private final InterestReport interestReport;

    CompleteReport(BankAccount account, TransactionReport transactionReport, InterestReport interestReport) {
        super(account);
        this.transactionReport = transactionReport;
        this.interestReport = interestReport;
    }

    String name() {
        return "Complete Report";
    }

    List<String> items(DateRange period) {
        List<String> items = new ArrayList<>();
        items.add("Interest payments");
        items.addAll(interestReport.items(period));
        items.add("Transactions");
        items.addAll(transactionReport.items(period));
        return items;
    }
}

public class StatementTemplate {
    private final BankAccount account;
    private final TransferRepository transferRepository;
    private final InterestPaymentRepository interestPaymentRepository;

    public StatementTemplate(BankAccount account, TransferRepository transferRepository, InterestPaymentRepository interestPaymentRepository) {
        this.account = account;
        this.transferRepository = transferRepository;
        this.interestPaymentRepository = interestPaymentRepository;
    }

    public String transactionReport(DateRange period) {
        return new TransactionReport(account, transferRepository).report(period);
    }

    public String interestReport(DateRange period) {
        return new InterestReport(account, interestPaymentRepository).report(period);
    }

    public String completeReport(DateRange period) {
        return new CompleteReport(
                account,
                new TransactionReport(account, transferRepository),
                new InterestReport(account, interestPaymentRepository)
        ).report(period);
    }
}
