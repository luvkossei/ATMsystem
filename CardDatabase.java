import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;


public class CardDatabase {
    private final Map<String, Card> cards = new HashMap<>();
    private final Map<String, UserAccount> accounts = new HashMap<>();

    public CardDatabase() {
        addTestData();
    }

    public void addTestData() {
        Card card1 = new Card(
            "1234-5678-9012-3456",
            HashUtil.sha256("0000"),
            LocalDate.parse("2025-12-31")
        );

        UserAccount acc1 = new UserAccount("ACC-001", 100000);

        cards.put(card1.getCardNumber(), card1);
        accounts.put(card1.getCardNumber(), acc1);
    }

    public UserAccount getAccount(String cardNumber) {
        return accounts.get(cardNumber);
    }

    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }
}