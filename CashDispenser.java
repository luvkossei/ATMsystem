public class CashDispenser {
    private int cashStock = 1000000; // 初期残高

    public CashDispenser() {
        // コンストラクタ（必要なら初期化処理を書く）
    }

    public boolean dispense(int amount) {
        if (amount <= 0) {
            System.err.println("引き出し額は1円以上で指定してください。");
            return false;
        }
        if (cashStock >= amount) {
            cashStock -= amount;
            System.out.printf("%,d円を払い出しました。\n", amount);
            return true;
        } else {
            System.err.println("ATM内に十分な現金がありません。");
            return false;
        }
    }

    public void reload(int amount) {
        cashStock += amount;
        System.out.printf("ATMに %,d円を補充しました。\n", amount);
    }
}