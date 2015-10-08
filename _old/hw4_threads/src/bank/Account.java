package bank;

public class Account {

    // id number, the current balance for the account,
    // and the number of transactions that have occurred on the account
    private int id;
    private int balance;
    private int transCount;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
        transCount = 0;
    }

    public int getId() {
        return id;
    }
    public int getBalance() {
        return balance;
    }
    public int getTransCount() {
        return transCount;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        transCount++;
    }

    public synchronized void withdraw(int amount) {

        balance -= amount;
        transCount++;
    }

    @Override
    public String toString() {
        return "acct:" + getId() +
                " bal:" + getBalance() +
                " trans:" + getTransCount();
    }
}
