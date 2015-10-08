package bank;

/** Transaction is a simple class that stores
 *  information on each transaction.
 *  Transaction is immutable.*/
public class Transaction {

    final private Account from;
    final private Account to;
    final private int amount;

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
