package bank2;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class TransReader implements Runnable {

    private Bank bank;

    public TransReader(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting...");
        try {
            readTransactions();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done");
    }

    private void readTransactions() throws InterruptedException {

        InputStream is = getClass().getClassLoader().getResourceAsStream(bank.getFileName());
        Scanner s = new Scanner(is);
        while (s.hasNextLine()) {
            bank.getTransactions().put(new Transaction(s.nextLine()));
        }
        for (int i = 0; i < bank.getNumberOfThreads(); i++) {
            bank.getTransactions().put(Transaction.NULL_TRANS);
        }

    }
}
