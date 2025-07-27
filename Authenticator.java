public class Authenticator {
    private int remainingAttempts = 3;

    public boolean verify(Card card, String pin) {
        if (remainingAttempts <= 0) return false;

        boolean result = card.verifyPin(pin);
        if (!result) remainingAttempts--;
        return result;
    }

    public boolean isLocked() {
        return remainingAttempts <= 0;
    }
}