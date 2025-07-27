import java.util.ArrayList;
import java.util.List;

public class UserAccount{
    private final String accountId;
    private int balance;
    private final List<TransactionRecord> history;

    public UserAccount(String accountId,int initialBalance){
        this.accountId = accountId;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(int amount){
        if(balance >= amount){
            balance -= amount;
            return true;
        }else{
            return false;
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void addTransaction(TransactionRecord record) {
        history.add(record);
    }

    public synchronized List<TransactionRecord> getHistory() {
        return new ArrayList<>(history);
    }

    public String getAccountId(){
        return accountId;
    }
}