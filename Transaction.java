public abstract class Transaction {
    protected UserAccount account;
    
    public Transaction(UserAccount account) {
        this.account = account;
    }
    
    public abstract void execute();
}