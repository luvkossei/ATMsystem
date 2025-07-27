public class CashReceiver {
    public boolean acceptCash(int amount) {
        System.out.println("現金 " + amount + "円を受け取りました。");
        return true; // 常に成功でよい
    }
}