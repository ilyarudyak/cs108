package bank2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Here we use separate classes for producer and consumer
 * (TransReader and TransWorker). (1) We create n + 1 threads where n
 * is the number of threads we used to create our Bank object.
 * We create n consumer tasks (one per thread) and 1 producer task.
 * Can we create different number tasks and threads?
 * (2) We stop these threads using NULL_TRANS that we put into queue
 * in producer (one per task). (3) We don't use countdown latch to
 * manage execution - we use awaitTermination() with some large parameter.
 */
public class Bank {

    public static final int INITIAL_BALANCE = 1000;
    public static final int NUMBER_OF_ACCOUNTS = 20;
    public static final int QUEUE_SIZE = 10;

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

        transactions = new ArrayBlockingQueue<>(QUEUE_SIZE);
    }

    public void printAccounts() {
        for(Account a: accounts) {
            System.out.println(a);
        }
    }

    // getters
    public List<Account> getAccounts() {
        return accounts;
    }
    public BlockingQueue<Transaction> getTransactions() {
        return transactions;
    }
    public int getNumberOfThreads() {
        return numberOfThreads;
    }
    public String getFileName() {
        return fileName;
    }

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank(Integer.parseInt(args[0]), args[1]);

        ExecutorService es = Executors.newFixedThreadPool(bank.getNumberOfThreads() + 1);
        for (int i = 0; i < bank.getNumberOfThreads(); i++) {
            es.execute(new TransWorker(bank));
        }
        es.execute(new TransReader(bank));

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        bank.printAccounts();

    }
}
















