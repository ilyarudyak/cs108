package bank;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;


/**
 * The Bank is responsible for:
 *
 *  1) reading transactions from the file, and
 *  2) printing out all the account values when everything is done.
 *
 *  and starting up the worker threads .
 *  */
public class Bank {

    private static final int NUMBER_OF_ACCOUNTS = 20;
    private static final int INITIAL_BALANCE = 1000;
    private static final int NUMBER_OF_TRANSACTIONS = 20;
    private static final int NUMBER_OF_THREADS = 25;
    private static final String  FILE_NAME = "data/5k.txt";
    private static final Transaction NULL_TRANS = new Transaction(null,null,0);

    // accounts in the bank
    private List<Account> accounts;
    // it is used to communicate between the main thread and the worker threads
    private static BlockingQueue<Transaction> queue;
    // we will print accounts when all worker threads are done
    private static CountDownLatch latch;

    public Bank() {

        accounts = new ArrayList<>();
        for (int i=0; i<NUMBER_OF_ACCOUNTS; i++)
            accounts.add(new Account(i, INITIAL_BALANCE));

        queue = new ArrayBlockingQueue<>(NUMBER_OF_TRANSACTIONS);
        latch = new CountDownLatch(NUMBER_OF_THREADS);

        // start worker threads
        for (int i=0; i<NUMBER_OF_THREADS; i++)
            new Thread(new Worker(latch)).start();
    }

    // 1) reading transactions from the file
    public void readFromFile() {

        try (BufferedReader in = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = in.readLine()) != null) {
//                System.out.println(line);
                queue.put(lineToTransaction(line));
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // file reading is done,
        // now send end signals to workers one per thread.
        for (int i = 0; i < NUMBER_OF_THREADS; i++)
            try {
                queue.put(NULL_TRANS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private Transaction lineToTransaction(String line) {
        String[] l = line.split("\\s+");
        return new Transaction(
                findAccountByID(Integer.parseInt(l[0].trim())),
                findAccountByID(Integer.parseInt(l[1].trim())),
                Integer.parseInt(l[2].trim()));
    }

    private Account findAccountByID(int id) {
        for (Account a: accounts) {
            if (a.getId() == id)
                return a;
        }
        return null;
    }

    // 2) printing out all the account values when everything is done
    private void printAccounts() {
        try {
            latch.await();
        } catch (InterruptedException ignored) {}

        for(Account a: accounts)
            System.out.println(a);
    }

    /**
     * Worker check the queue for transactions.
     * If they find a transaction they should process it.
     * If the queue is empty, they will wait for the Bank
     * class to read in another transaction */
    private static class Worker implements Runnable{

        private CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Transaction t = queue.take();
                    if (!t.equals(NULL_TRANS))
                        process(t);
                    else
                        break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " done");
            latch.countDown();
        }

        private void process(Transaction t) {
            t.getFrom().withdraw(t.getAmount());
            t.getTo().deposit(t.getAmount());
        }
    }// Worker

    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.readFromFile();
        bank.printAccounts();

    } // main()
}


















