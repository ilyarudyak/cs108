import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adjacent chars that are the same.
	 * @param s
	 * @return max run length
	 */
	public static int maxRun(String s) {

        if (s.isEmpty()) return 0;

        int max = 1, count = 1;
        for(int i=0; i<s.length()-1; i++) {

            if (s.charAt(i) == s.charAt(i+1)) {
                count++;

            }
            else {
                if (count > max)
                    max = count;
                count = 1;
            }
//            System.out.println(count+" "+max);
        }
		return max;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param s
	 * @return blown up string
	 */
	public static String blowup(String s) {

        int i=1;
        StringBuilder sb = new StringBuilder();

        if (s.isEmpty()) { return ""; }
        else if (s.length() == 1) {
            if (!Character.isDigit(s.charAt(0))) return s;
            else return "";
        }
        else if (s.length() >= 2) {

            // if string starts with a letter
            if (!Character.isDigit(s.charAt(0)))
                sb.append(s.charAt(0));
            else {
                build(sb, s.charAt(1), Integer.parseInt(s.substring(0, 1)));
            }

            while (i < s.length() - 1) {

                // current character is digit
                if (Character.isDigit(s.charAt(i))) {
                    build(sb, s.charAt(i + 1), Integer.parseInt(s.substring(i, i + 1)));
                }

                // current character is NOT digit
                else if (!Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                }
                i++;
            }

            // if last character is not digit
            if (!Character.isDigit(s.charAt(s.length() - 1))) {
                    sb.append(s.charAt(s.length() - 1));
            }
        }
        // else: ignore last digit

        return sb.toString();
	}

    private static void build(StringBuilder sb, Character s, int n) {

        for(int i=0; i<n; i++) {
            sb.append(s);
        }
    }
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {

        Set<String> seta = buildSet(a, len);
        Set<String> setb = buildSet(b, len);
        seta.retainAll(setb);

        return !seta.isEmpty();
	}

    // build set of all substring of length l
    private static Set<String> buildSet(String s, int len) {

        if (len < 1 || s.isEmpty()) throw new IllegalArgumentException("len < 1");

        Set<String> set = new HashSet<>();
        for (int i=0; i<s.length()-len+1; i++) {
            set.add(s.substring(i,i+len));
//            System.out.println(i);
        }
        return set;
    }

    public static void main(String[] args) {

        String s = "xxyyyz";
        System.out.println(buildSet(s, 1));
    }
}











