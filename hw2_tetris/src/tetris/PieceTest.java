package tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
    private Piece stick, l1, s1;
	private Piece s, sRotated;
    private Piece square;

	@Before
	public void setUp() throws Exception {
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

        stick = new Piece(Piece.STICK_STR);
        l1 = new Piece(Piece.L1_STR);
        s1 = new Piece(Piece.S1_STR);
        square = new Piece(Piece.SQUARE_STR);
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSampleSize1() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());


	}

    @Test
    public void testSampleSize2() {

        assertEquals(1, stick.getWidth());
        assertEquals(4, stick.getHeight());

        assertEquals(2, l1.getWidth());
        assertEquals(3, l1.getHeight());

        assertEquals(3, s1.getWidth());
        assertEquals(2, s1.getHeight());

    }

    @Test
    public void testSampleSize3() {

        // Now try with some other piece, made a different way
        Piece l = new Piece(Piece.STICK_STR);
        assertEquals(1, l.getWidth());
        assertEquals(4, l.getHeight());
    }
	
	
	// Test the skirt returned by a few pieces
	@Test
	public void testSampleSkirt1() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
	}

    @Test
    public void testSampleSkirt2() {

        assertTrue(Arrays.equals(new int[]{0}, stick.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 0}, l1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 0, 1}, s1.getSkirt()));
    }

    @Test
    public void testEquals() {

        assertTrue(new Piece("0 0	0 1	 0 2  0 3").equals(new Piece("0 0	0 1	 0 2  0 3")));
        assertTrue(new Piece("0 0	0 1	 0 2  0 3").equals(new Piece("0 1	0 0	 0 2  0 3")));
        assertFalse(new Piece("0 5	0 1	 0 2  0 3").equals(new Piece("0 0	0 1	 0 2  0 3")));

        // pyr needs 4 rotations to get back
        assertTrue(pyr1.equals(pyr4.computeNextRotation()));
    }

    @Test
    public void testRotation() {

        // after some rotations we have to get back
        assertTrue(stick.computeNextRotation()
                .computeNextRotation().equals(stick));
        assertTrue(l1.computeNextRotation()
                .computeNextRotation()
                .computeNextRotation()
                .computeNextRotation().equals(l1));
        assertTrue(s1.computeNextRotation()
                .computeNextRotation()
                .computeNextRotation()
                .computeNextRotation().equals(s1));
        assertTrue(square.computeNextRotation().equals(square));

        // test rotation of s1
        Piece s1r = s1.computeNextRotation();
        assertEquals(3, s1r.getHeight());
        assertEquals(2, s1r.getWidth());
        assertTrue(Arrays.equals(new int[] {1, 0}, s1r.getSkirt()));
        assertEquals(new Piece("0 1  0 2  1 0  1 1"), s1r);


    }

    @Test
    public void testFastRotation() {

        Piece[] pieces = Piece.getPieces();

        Piece stick = pieces[Piece.STICK];
        assertEquals(stick, new Piece(Piece.STICK_STR));
        assertEquals(stick.fastRotation(),
                new Piece(Piece.STICK_STR).computeNextRotation());
        assertEquals(stick.fastRotation().fastRotation(),
                new Piece(Piece.STICK_STR));

        Piece l1 = pieces[Piece.L1];
        // initial
        assertEquals(l1, new Piece(Piece.L1_STR));
        // rotations 1-3
        assertEquals(l1.fastRotation(),
                new Piece(Piece.L1_STR).computeNextRotation());

        assertEquals(l1.fastRotation()
                        .fastRotation(),
                new Piece(Piece.L1_STR)
                        .computeNextRotation()
                        .computeNextRotation());

        assertEquals(l1.fastRotation()
                        .fastRotation()
                        .fastRotation(),
                new Piece(Piece.L1_STR)
                        .computeNextRotation()
                        .computeNextRotation()
                        .computeNextRotation());

        // final rotation
        assertEquals(l1.fastRotation()
                        .fastRotation()
                        .fastRotation()
                        .fastRotation(),
                new Piece(Piece.L1_STR));

    }
}
















