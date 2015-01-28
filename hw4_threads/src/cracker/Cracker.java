package cracker;

import com.google.common.base.Stopwatch;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Cracker  {

	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();

	public static final int NUMBER_OF_THREADS = 20;
	public static final String hexString = "adeb6f2a18fe33af368d91b09587b68e3abcb9a7";

	private static String password;
	private static CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

	public String getPassword() {
		return password;
	}

	public Cracker() {

		// start worker threads
		for (int i=0; i<NUMBER_OF_THREADS; i++)
			new Thread(new Worker(i, i+CHARS.length/NUMBER_OF_THREADS)).start();
	}

	private static class Worker implements Runnable  {

		private char[] ch;
		public Worker(int from, int to) {
			ch = Arrays.copyOfRange(CHARS, from, to);
		}

		private String hash(String message)  {

			// get message digest
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			// calculate digest
			return Hex.encodeHexString(md.digest(message.getBytes()));
		}

		// brute-force crack; input - 86f7... output - a
		private void crack1()  {

			for(int i=0; i<ch.length; i++) {
				String hash = hash(Character.toString(ch[i]));
				if (hash.equals(hexString))
					password =  Character.toString(ch[i]);
			}

		}

		private void crack2()  {

			for(int i=0; i<ch.length; i++) {
				for(int j=0; j<CHARS.length; j++) {
					String hash = hash(Character.toString(ch[i]) +
							Character.toString(CHARS[j]));
					if (hash.equals(hexString))
						password =  Character.toString(ch[i]) +
								Character.toString(CHARS[j]);
				}
			}

		}

		@Override
		public void run() {

			crack1();
			crack2();

			System.out.println(Thread.currentThread().getName() + " done");
			latch.countDown();

		}

	} // Worker
	
	// possible test values:
	// a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
	// fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
	// a! 34800e15707fae815d7c90d49de44aca97e2d759
	// xyz 66b27417d37e024c46526c2f6d358a754fc552f3

	public static void main(String[] args)  {

		Stopwatch stopwatch = Stopwatch.createStarted();
		Cracker c = new Cracker();

		try {
			latch.await();
		} catch (InterruptedException ignored) {}

		System.out.println(c.getPassword());
		stopwatch.stop();
		System.out.println("time: " + stopwatch);

	}


}
