public class BalanceInquiry extends Transaction {
    public BalanceInquiry(UserAccount account) {
        super(account);
    }

    @Override
    public void execute() {
        System.out.printf("現在の残高: %,d円%n", account.getBalance());
    }
}