// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class StringCodeTest {
    //
    // blowup
    //



	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
	}
	
	@Test
	public void testBlowup2() {
		// things with digits

		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));

		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));

		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}

	@Test
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));

		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}


	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}

	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}

	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}

	//
	// stringIntersect
	//
    @Test
    public void stringIntersect1() {

        // trivial case
        assertTrue(StringCode.stringIntersect("xxyyzz", "xx", 2));
        assertFalse(StringCode.stringIntersect("xxyyzz", "aa", 2));

        // works with len == 1
        assertTrue(StringCode.stringIntersect("xxyyzz", "xx", 1));
        assertFalse(StringCode.stringIntersect("xxyyzz", "aa", 1));

    }

    @Test(expected = IllegalArgumentException.class)
    public void stringIntersect2() {
        StringCode.stringIntersect("", "aa", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringIntersect3() {
        StringCode.stringIntersect("a", "aa", 0);
    }

    @Test
    public void stringIntersect4() {

        // works when len > s.length()
        assertFalse(StringCode.stringIntersect("xxyyzz", "xx", 3));

        // works with the beginning
        assertTrue(StringCode.stringIntersect("xxxyyzz", "aaaxxxcc", 3));
        assertTrue(StringCode.stringIntersect("xzzyyzz", "aaaxxxcc", 1));

        // works with the end
        assertTrue(StringCode.stringIntersect("xxxyyyz", "aaazzzcc", 1));
        assertTrue(StringCode.stringIntersect("xxxyyzz", "aaazzzcc", 2));
        assertTrue(StringCode.stringIntersect("xxxyzzz", "aaazzzzz", 3));
    }
}












