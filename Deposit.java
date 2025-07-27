public class Deposit extends Transaction {

    private final int amount; // 預入金額
    private final CashReceiver receiver; // 現金受取機
    private final Logger logger; // ログ記録用

    public Deposit(UserAccount account, int amount, CashReceiver receiver, Logger logger) {
        super(account);
        this.amount = amount;
        this.receiver = receiver;
        this.logger = logger;
    }

    public void execute() {
        if (amount <= 0) {
            throw new IllegalArgumentException("預入金額が不正です");
        }

        if (!receiver.acceptCash(amount)) {
            throw new IllegalStateException("現金の受取に失敗しました");
        }

        account.deposit(amount);

        TransactionRecord record = new TransactionRecord(
            java.time.LocalDateTime.now(),
            "DEPOSIT",
            amount,
            account.getBalance()
        );

        logger.log(record);
    }

    public int getAmount() {
        return amount;
    }
}