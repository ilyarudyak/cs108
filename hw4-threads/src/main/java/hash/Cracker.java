package hash;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * We crack password using brute-force. We generate all combinations
 * of 3 symbols from SYMBOLS (we don't generalize the problem). We then
 * calculate SHA of this combination and compare it to provided hex string.
 * We use a library to generate these combinations. We use combinations with
 * order.
 *
 * To use threads we do the following trick. We divide SYMBOLS into 2 parts
 * (we use 2 threads - again we can generalize that). Worker 1 works with symbol
 * from part1 + all combinations of 2 symbols (so total length == 3). Worker 2 do
 * the same with part2.
 * */
public class Cracker {
    // Array of chars used to produce strings
    public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();
    public static final String[] SYMBOLS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".split("");

    /*
     Given a byte[] array, produces a hex String,
     such as "234a6f". with 2 chars for each byte in the array.
     (provided code)
    */
    public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    /*
     Given a string of hex byte values such as "24a26f", creates
     a byte[] array of those values, one byte value -128..127
     for each 2 chars.
     (provided code)
    */
    public static byte[] hexToArray(String hex) {
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            result[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return result;
    }

    // possible test values:
    // a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
    // fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
    // a! 34800e15707fae815d7c90d49de44aca97e2d759
    // xyz 66b27417d37e024c46526c2f6d358a754fc552f3

    public static String getSHA(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return hexToString(digest);
    }

    public static String vectorToString(ICombinatoricsVector<String> v) {
        List<String> list = v.getVector();
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void crack(String[] symbolsPart, String hexStr) throws NoSuchAlgorithmException {

        ICombinatoricsVector<String> originalVector = Factory.createVector(SYMBOLS);
        Generator<String> gen = Factory.createPermutationWithRepetitionGenerator(originalVector, 2);

        for (String s: symbolsPart) {

            // we use here generator so we stop generating
            // as soon as we find a solution
            for (ICombinatoricsVector<String> perm : gen) {
                String password = s + vectorToString(perm);
                String shaStr = getSHA(password);
                if (shaStr.equals(hexStr)) {
                    // we just print password if its SHA is
                    // the same as provided
                    System.out.println(password);
                    return;
                }
            }
        }
    }

    private static class Worker implements Runnable {

        private String[] symbolsPart;
        private String hexStr;

        /**
         * Worker deals with its part of SYMBOLS for the 1st symbol.
         * It generates combination inside. And it stops as long as
         * it finds a solution. We use static class as our other methods.
         * */
        public Worker(String[] symbolsPart, String hexStr) {
            this.symbolsPart = symbolsPart;
            this.hexStr = hexStr;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starting...");
            try {
                crack(symbolsPart, hexStr);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

//        this is just test of crack() without threading
//        String[] symbolsPart = {"a", "b", "x"};
//        crack(symbolsPart, "66b27417d37e024c46526c2f6d358a754fc552f3");

        ExecutorService es = Executors.newFixedThreadPool(2);

        String hexStr = "66b27417d37e024c46526c2f6d358a754fc552f3";
        String[] symbolsPart1 = Arrays.copyOfRange(SYMBOLS, 0, SYMBOLS.length/2);
        String[] symbolsPart2 = Arrays.copyOfRange(SYMBOLS, SYMBOLS.length/2, SYMBOLS.length);

        es.execute(new Worker(symbolsPart1, hexStr));
        es.execute(new Worker(symbolsPart2, hexStr));

        es.shutdown();

    }
}
