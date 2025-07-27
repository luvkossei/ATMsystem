import java.time.LocalDateTime;

public class TransactionRecord {
    private final LocalDateTime timestamp;
    private final String type;
    private final int amount;
    private final int resultingBalance;

    public TransactionRecord(LocalDateTime timestamp, String type, int amount, int resultingBalance) {
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.resultingBalance = resultingBalance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public int getResultingBalance(){
        return resultingBalance;
    }

    @Override
    public String toString(){
        return String.format(
            "[%s] %s: %,d円 (残高: %,d円)",
            timestamp.toString(),
            type,
            amount,
            resultingBalance
        );
    }
}