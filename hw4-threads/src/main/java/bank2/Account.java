package bank2;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class Account {

    private final int id;
    private int transCount;
    private int balance;

    public Account(int accountId, int currentBalance) {
        this.id = accountId;
        this.transCount = 0;
        this.balance = currentBalance;
    }

    public void increaseCount() {
        transCount++;
    }
    public void debit(int amount) {
        balance += amount;
    }
    public void credit(int amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return  "acct:" + id +
                " bal:" + balance +
                " trans:" + transCount;
    }
}
