import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger{
    private final String logFile;
    private final List<TransactionRecord> history;

    public Logger(String logFile) {
        this.logFile = logFile;
        this.history = new ArrayList<>();
    }

    public synchronized void log(TransactionRecord record) {
        history.add(record);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(record.toString());
            writer.newLine();
        }catch (IOException e) {
            System.err.println("ログファイル書き込みエラー: " + e.getMessage());
        }
    }

    public synchronized List<TransactionRecord> getHistory() {
        return new ArrayList<>(history);
    }
}