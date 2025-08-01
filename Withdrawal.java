public class Withdrawal extends Transaction {
    private int amount;
    private CashDispenser dispenser;
    private final Logger logger;

    public Withdrawal(UserAccount account, int amount, CashDispenser dispenser,Logger logger) {
        super(account);
        if(amount <= 0) throw new IllegalArgumentException("金額は正の値でなければなりません");
        this.amount = amount;
        this.dispenser = dispenser;
	this.logger = logger;
    }

    @Override
    public void execute() {
        if (account.getBalance() < amount) {
            throw new IllegalStateException("残高不足" + account.getBalance() + "円");
        }
        if(!dispenser.dispense(amount)) {
            throw new IllegalStateException("現金排出に失敗しました");
        }
        account.withdraw(amount);

	TransactionRecord record = new TransactionRecord(
            java.time.LocalDateTime.now(),
            "WITHDRAWAL", 
            amount,
            account.getBalance() 
        );
        logger.log(record); 
    }
}
