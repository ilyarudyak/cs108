package tetris;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.Arrays;

public class BoardTest {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;
    Piece[] pieces;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	@Before
	public void setUp() throws Exception {
		b = new Board(3, 6);
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();

        pieces = Piece.getPieces();
	}
	
	// Check the basic width/height/max after the one placement
	@Test @Ignore
	public void testSample1() {

        b.place(pyr1, 0, 0);

		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));

		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
	// Place sRotated into the board, then check some measures
	@Test @Ignore
	public void testSample2() {


		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}

    @Test @Ignore
    public void testPlace() {

        assertEquals(Board.PLACE_ROW_FILLED, b.place(pieces[Piece.PYRAMID], 0, 0));
        assertArrayEquals(new int[] {3,1,0,0,0,0}, b.widths);
        assertArrayEquals(new int[] {1,2,1}, b.heights);

        assertEquals(Board.PLACE_ROW_FILLED, b.place(pieces[Piece.PYRAMID]
                .fastRotation(), 1, 1));
        assertArrayEquals(new int[] {3,2,2,1,0,0}, b.widths);
        assertArrayEquals(new int[] {1,3,4}, b.heights);
        assertEquals(4, b.getMaxHeight());

        assertEquals(Board.PLACE_ROW_FILLED, b.place(pieces[Piece.STICK], 0, 1));
        assertArrayEquals(new int[] {3,3,3,2,1,0}, b.widths);
        assertArrayEquals(new int[] {5,3,4}, b.heights);
        assertEquals(5, b.getMaxHeight());

        System.out.println(b);

    }

    @Test
    public void testClearRows() {

        b.place(pieces[Piece.PYRAMID], 0, 0);
        b.place(pieces[Piece.PYRAMID]
                .fastRotation(), 1, 1);
        b.place(pieces[Piece.STICK], 0, 1);
        b.place(pieces[Piece.S2].fastRotation(), 1, 3);

        System.out.println(b);

        b.clearRows();

        System.out.println(b);
    }
	

	
	
}
