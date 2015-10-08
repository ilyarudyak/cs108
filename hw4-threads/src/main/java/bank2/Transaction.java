package bank2;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class Transaction {

    private final int creditAccountId;
    private final int debitAccountId;
    private final int amount;

    public static final Transaction NULL_TRANS = new Transaction(-1,0,0);

    public Transaction(int creditAccount, int debitAccount, int amount) {
        this.creditAccountId = creditAccount;
        this.debitAccountId = debitAccount;
        this.amount = amount;
    }

    public Transaction(String line) {
        String[] parts = line.split(" ");
        creditAccountId = Integer.parseInt(parts[0].trim());
        debitAccountId = Integer.parseInt(parts[1].trim());
        amount = Integer.parseInt(parts[2].trim());
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
        return creditAccountId + " " + debitAccountId + " " + amount;
    }
}
