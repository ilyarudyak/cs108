package bank;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class Account {

    private final int accountId;
    private int transCount;
    private int currentBalance;

    public Account(int accountId, int currentBalance) {
        this.accountId = accountId;
        this.transCount = 0;
        this.currentBalance = currentBalance;
    }

    public int getAccountId() {
        return accountId;
    }
    public int getTransCount() {
        return transCount;
    }
    public int getCurrentBalance() {
        return currentBalance;
    }

    public void increaseCount() {
        transCount++;
    }
    public void debit(int amount) {
        currentBalance += amount;
    }
    public void credit(int amount) {
        currentBalance -= amount;
    }

    @Override
    public String toString() {
        return  "acct:" + getAccountId() +
                " bal:" + getCurrentBalance() +
                " trans:" + getTransCount();
    }
}
