import java.util.Scanner;

public class ATMSystem {

    private Card currentCard;
    private UserAccount currentAccount;
    private Authenticator authenticator;
    private CardDatabase cardDB;
    private Menu menu;

    // コンストラクタ
    public ATMSystem() {
        this.authenticator = new Authenticator();
        this.cardDB = new CardDatabase();
        this.menu = new Menu();
    }

    // プログラムのエントリポイント
    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.start();
    }

    // ATMシステム起動
    public void start() {
        System.out.println("ATMシステムを起動しました。");
        handleCardInsertion();
        if (currentAccount != null) {
            processTransaction();
        }
        System.out.println("ATMシステムを終了します。");
    }

    // カード挿入・PIN認証処理
    private void handleCardInsertion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("カード番号を入力してください(テスト用番号:1234-5678-9012-3456): ");
        String cardNumber = scanner.nextLine().trim();

        Card card = cardDB.getCard(cardNumber);
        if (card == null) {
            System.out.println("カードが見つかりませんでした。");
            return;
        }

        if (card.isExpired()) {
            System.out.println("このカードは有効期限切れです。");
            return;
        }

        int attempts = 3;
        boolean verified = false;
        while (attempts > 0) {
            System.out.print("PINコードを入力してください(テスト用pin:0000): ");
            String inputPin = scanner.nextLine().trim();
            verified = authenticator.verify(card, inputPin);
            if (verified) {
                break;
            } else {
                attempts--;
                System.out.println("PINが間違っています。残り試行回数: " + attempts);
            }
        }

        if (!verified) {
            System.out.println("PIN入力に3回失敗しました。カードがロックされました。");
            return;
        }

        this.currentCard = card;
        this.currentAccount = cardDB.getAccount(cardNumber);
        if (currentAccount == null) {
            System.out.println("口座情報が見つかりませんでした。");
        }
    }

    // メニュー表示・取引処理
    private void processTransaction() {
        boolean continueTransaction = true;
        Scanner scanner = new Scanner(System.in);

        while (continueTransaction) {
            menu.showMainMenu();
            System.out.print("操作を選択してください (1:残高照会 2:預け入れ 3:引き出し 4:終了): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("無効な入力です。");
                continue;
            }

            switch (choice) {
                case 1:
                    int balance = currentAccount.getBalance();
                    System.out.println("現在の残高: " + balance + "円");
                    break;

                case 2:
                    System.out.print("入金額を入力してください: ");
                    int depositAmount = Integer.parseInt(scanner.nextLine().trim());
                    currentAccount.deposit(depositAmount);
                    System.out.println(depositAmount + "円を入金しました。");
                    break;

                case 3:
                    System.out.print("引き出し額を入力してください: ");
                    int withdrawAmount = Integer.parseInt(scanner.nextLine().trim());
                    boolean success = currentAccount.withdraw(withdrawAmount);
                    if (success) {
                        System.out.println(withdrawAmount + "円を引き出しました。");
                    } else {
                        System.out.println("残高不足です。");
                    }
                    break;

                case 4:
                    continueTransaction = false;
                    System.out.println("カードを返却します。ご利用ありがとうございました。");
                    break;

                default:
                    System.out.println("無効な選択です。1-4を入力してください。");
            }
        }
    }
}
