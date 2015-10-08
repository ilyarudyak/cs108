package bank2;


/**
 * Created by ilyarudyak on 10/8/15.
 */
public class TransWorker implements Runnable{

    private Bank bank;

    public TransWorker(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting...");
        try {
            while (true){
                Transaction t = bank.getTransactions().take();
                if (!t.equals(Transaction.NULL_TRANS)) {
                    process(t);
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done");
    }

    private void process(Transaction t) {
        int amount = t.getAmount();

        Account creditAccount = bank.getAccounts().get(t.getCreditAccountId());
        creditAccount.credit(amount);
        creditAccount.increaseCount();

        Account debitAccount = bank.getAccounts().get(t.getDebitAccountId());
        debitAccount.debit(amount);
        debitAccount.increaseCount();
    }
}
