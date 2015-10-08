package bank;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class Transaction {

    public static final Transaction NULL_TRANS = new Transaction(-1,0,0);

    private final int creditAccountId;
    private final int debitAccountId;
    private final int amount;

    public Transaction(int creditAccount, int debitAccount, int amount) {
        this.creditAccountId = creditAccount;
        this.debitAccountId = debitAccount;
        this.amount = amount;
    }

    public Transaction(String[] parts) {
        this(   Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
    }

    public int getCreditAccountId() {
        return creditAccountId;
    }

    public int getDebitAccountId() {
        return debitAccountId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "creditAccountId=" + creditAccountId +
                ", debitAccountId=" + debitAccountId +
                ", amount=" + amount +
                '}';
    }
}
