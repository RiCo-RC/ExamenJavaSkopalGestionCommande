package transactionLogger;

public class STransactionLogger {

    private static TransactionLogger instance;

    private STransactionLogger() {}

    public static TransactionLogger getInstance() {
        if (instance == null) {
            instance = new TransactionLogger();
        }
        return instance;
    }
}