package bank;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by ilyarudyak on 10/8/15.
 */
public class Bank {

    public static final int INITIAL_BALANCE = 1000;
    public static final int NUMBER_OF_ACCOUNTS = 20;

    private List<Account> accounts;
    private BlockingQueue<Transaction> transactions;

    private int numberOfThreads;
    private String fileName;

    public Bank(int numberOfThreads, String fileName) {
        accounts = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            accounts.add(new Account(i, INITIAL_BALANCE));
        }

        this.numberOfThreads = numberOfThreads;
        this.fileName = fileName;

        transactions = new ArrayBlockingQueue<>(10);
    }

    public void readTransactions() throws InterruptedException {

        System.out.println("start reading...");
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner s = new Scanner(is);
        while (s.hasNextLine()) {
            transactions.put(new Transaction(s.nextLine().split(" ")));
//            System.out.println(transactions.size());
        }
        for (int i = 0; i < numberOfThreads; i++) {
            transactions.put(Transaction.NULL_TRANS);
        }
        System.out.println("done reading...");
    }

    public void printAccounts() {
        for(Account a: accounts) {
            System.out.println(a);
        }
    }

    public void processFile() throws InterruptedException {

        System.out.println("created es...");
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            System.out.println("assigned workers...");
            Worker worker = new Worker();
            es.execute(worker);
        }

        es.shutdown();
        readTransactions();
        es.awaitTermination(10, TimeUnit.SECONDS);
        printAccounts();

    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starting...");
            try {
                while (true){
                    Transaction t = transactions.take();
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

            Account creditAccount = accounts.get(t.getCreditAccountId());
            creditAccount.credit(amount);
            creditAccount.increaseCount();

            Account debitAccount = accounts.get(t.getDebitAccountId());
            debitAccount.debit(amount);
            debitAccount.increaseCount();
        }
    }

    public static void main(String[] args) {


        Bank bank = new Bank(5, "5k.txt");
        try {
            bank.processFile();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
















