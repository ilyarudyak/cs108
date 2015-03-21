package tetris.board;

import org.junit.Before;
import org.junit.Test;
import tetris.piece.Piece;

import java.util.Random;

/**
 * Created by ilyarudyak on 3/20/15.
 */
public class BoardTestAuto {

    Board b;
    Random rand = new Random(0);
    Piece[] pieces;
    int iter;

    @Before
    public void setUp() throws Exception {
        b = new Board(10, 20);
        pieces = Piece.getPieces();
        iter = 100;
    }

    @Test
    public void testUndo() {

        drop(10, true);
        drop(iter, false);

        System.out.println(b);
    }

    private void drop(int n, boolean flag) {

        Piece piece;
        int x0, y0;
        for (int i=0; i<n; i++) {

            piece = pieces[rand.nextInt(pieces.length)];

            x0 = rand.nextInt(b.getWidth());
            y0 = b.dropHeight(piece, x0);

            b.place(piece, x0, y0);
            if (flag) { b.commit(); }
            else  { b.undo(); }
        }
    }


}
