// TabooTest.java
// Taboo class tests -- nothing provided.

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TabooTest {

    private static final int N = 100;
    private static final long SEED = 0;

    @Test
    public void noFollow1() {

        List<String> list = Arrays.asList("a", "c", "a", "b");
        Taboo taboo = new Taboo(list);

        assertEquals(new TreeSet<String>(Arrays.asList("b", "c")), taboo.noFollow("a"));
        assertTrue(taboo.noFollow("x").isEmpty());

    }

    @Test
    public void noFollow2() {


        Random random = new Random(SEED);
        List<String> list = new ArrayList<>(N);

        for (int i=0; i<N; i++) {
            char ch = (char) (97 + random.nextInt(26));
            list.add(Character.toString(ch));
        }

        Taboo taboo = new Taboo(list);

        assertEquals(new TreeSet<>(Arrays.asList("j", "x")), taboo.noFollow("z"));
        assertEquals(new TreeSet<>(Arrays.asList("b", "d", "h", "g")), taboo.noFollow("j"));

//        int count = 0;
//        System.out.println(list);
//        for (String s: list) {
//            if ("j".equals(s))
//                count++;
//        }
//        System.out.println(count);
    }

    @Test
    public void  reduce1() {
        Random random = new Random(SEED);
        List<String> rules = new ArrayList<>(N);

        for (int i=0; i<N; i++) {
            char ch = (char) (97 + random.nextInt(26));
            rules.add(Character.toString(ch));
        }

        Taboo taboo = new Taboo(rules);

        List<String> list = new ArrayList<>(Arrays.asList("z", "x", "j", "x", "z", "a"));
        taboo.reduce(list);
        assertEquals(new ArrayList<>(Arrays.asList("z", "z", "a")), list);
    }
}














