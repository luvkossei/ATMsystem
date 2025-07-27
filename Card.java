import java.time.LocalDate;

public class Card{
    private final String cardNumber;
    private final String hashedPin;
    private final LocalDate expiryDate;

    public Card(String cardNumber, String hashedPin, LocalDate expiryDate) {
        this.cardNumber = cardNumber;
        this.hashedPin = hashedPin;
        this.expiryDate = expiryDate;
    }

    public boolean verifyPin(String inputPin) {
        String hashedInput = HashUtil.sha256(inputPin);
        return hashedInput.equals(this.hashedPin);
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public String getCardNumber() {
        return cardNumber;
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}