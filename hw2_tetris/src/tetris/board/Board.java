// Board.java
package tetris.board;

import com.google.common.primitives.Ints;
import tetris.piece.TPoint;
import tetris.piece.Piece;

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{

    public static final int PLACE_OK = 0;
    public static final int PLACE_ROW_FILLED = 1;
    public static final int PLACE_OUT_BOUNDS = 2;
    public static final int PLACE_BAD = 3;

    // standard tetris is 10x20
	private int width;
	private int height;

	private boolean[][] grid;

	private boolean DEBUG = false;
	boolean committed;

    // how many filled spots
    // there are in each row
    int[] widths;

    // how many filled spots
    // there are in each column
    int[] heights;

    // copy of all variables for undo()
    private boolean[][] gridCopy;
    int[] widthsCopy;
    int[] heightsCopy;
	

	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
        gridCopy = new boolean[width][height];

		committed = true;

        widths = new int[height];
        heights = new int[width];
        widthsCopy = new int[height];
        heightsCopy = new int[width];
	}

    // ------------- grid getters ---------------
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}

    /**
     Returns true if the given block is filled in the board.
     Blocks outside of the valid width/height area
     always return true.

     We don't really use this method - we need different
     return codes for placement on occupied cell and
     outside the grid.
     */
    public boolean getGrid(int x, int y) {

        if (    x < 0 || x >= width  ||
                y < 0 || y >= height ||
                grid[x][y] ) {
            return true;
        }

        return false;
    }


    // ------------- column height --------------

    /**
     Returns the height of the given column --
     i.e. the y value of the highest block + 1.
     The height is 0 if the column contains no blocks.
     */
    public int getColumnHeight(int x) {
        return heights[x];
    }

	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.

     We don't use this in our code. Probably we have to create

	*/
	public int getMaxHeight() {	 
		return Ints.max(heights);
	}

    // ------------- row width ------------------

    /**
     Returns the number of filled blocks in
     the given row.
     */
    public int getRowWidth(int y) {
        return widths[y];
    }

    /////////////////////////////////////////////
    // ------------- main methods --------------
    ////////////////////////////////////////////
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {


        int result = 0;
        int[] skirt = piece.getSkirt();
        for (int i = 0; i < skirt.length && x+i < width; i++) {
            int y = heights[x + i] - skirt[i];
            if (y > result)
                result = y;
        }
        return result;
    }

	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x0, int y0) {

		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
        committed = false;
        backup();

        int x, y;
        int result = PLACE_OK;
        for (TPoint p: piece.getBody()) {

            x = x0 + p.x;
            y = y0 + p.y;
            if (isOutOfBounds(x, y)) { result = PLACE_OUT_BOUNDS; break; }
            else if (grid[x][y]) { result = PLACE_BAD; break; }
            else {
                grid[x][y] = true;
                updateWidthHeight(x, y);

                // if now this row is filled
                if (widths[y] == width) { result = PLACE_ROW_FILLED; }
            }
        }

        sanityCheck();
		return result;
	}
    private void updateWidthHeight(int x, int y) {
        // update widths and heights
        widths[y]++;
        if (y + 1 > heights[x]) {
            heights[x] = y + 1;
        }
    }
    private void backup() {
        // copy all variables for undo()
        widthsCopy = Arrays.copyOf(widths, widths.length);
        heightsCopy = Arrays.copyOf(heights, heights.length);

        for (int i=0; i<gridCopy.length; i++) {
            gridCopy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
    }
    private int getRowFilled() {

        for (int i=0; i<widths.length; i++) {
            if (widths[i] == width) { return i; }
        }

        return -1;
    }
    private boolean isOutOfBounds(int x, int y) {

        if (    x < 0 || x >= width ||
                y < 0 || y >= height ) {
            return true;
        }
        return false;
    }
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {

        // do clearRows() w/o place()
        if (committed) {
            backup();
        }

		int rowsCleared = 0;
        int indexRowFilled = getRowFilled();
        while (!(indexRowFilled == -1)) {
            clearRow(indexRowFilled);
            rowsCleared++;
            indexRowFilled = getRowFilled();

        }
		sanityCheck();
        committed = false;
		return rowsCleared;
	}
    private void clearRow(int y0) {

        for (int x=0; x<grid.length; x++) {
            for(int y=y0; y<grid[0].length-1; y++) {
                grid[x][y] = grid[x][y + 1];
            }
            grid[x][grid[0].length-1] = false;
        }

        // update widths and heights
        for (int x=0; x<heights.length; x++) {
            heights[x]--;
        }
        for (int y=y0; y<widths.length-1; y++) {
            widths[y] = widths[y+1];
        }
        widths[widths.length-1] = 0;
    }

    // ------------- undo and commit ------------

	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {

        // when the board is already in the
        // committed state silently do nothing
        if (committed) { return; }
		else {

//            widths = widthsCopy;
//            heights = heightsCopy;
//            grid = gridCopy;
            swap();

            committed = true;
        }
	}
    private void swap() {

        int[] temp = widthsCopy;
        widthsCopy = widths;
        widths = temp;

        temp = heightsCopy;
        heightsCopy = heights;
        heights = temp;

        boolean[][] gridtemp = gridCopy;
        gridCopy = grid;
        grid = gridtemp;
    }

	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}

    // ------------- debugging ------------------

    /**
     Checks the board for internal consistency -- used
     for debugging.
     */
    public void sanityCheck() {

        if (DEBUG) {

            int[] w = buildWidths();
            int[] h = buildHeight();
            assert(Arrays.equals(w, widths));
            assert(Arrays.equals(h, heights));

        }
    }
    private int[] buildHeight() {

        int[] h = new int[width];
        int count;

        for (int x=0; x<heights.length; x++) {
            count = 0;
            for (int y=0; y<widths.length; y++) {
                if(grid[x][y]) { count++; }
                else { break; }
            }
            h[x] = count++;
        }
        return h;

    }
    private int[] buildWidths() {

        int[] w = new int[height];
        int count;
        for (int y=0; y<widths.length; y++) {
            count = 0;
            for (int x=0; x<heights.length; x++) {
                if(grid[x][y]) { count++; }
            }
            w[y] = count;
        }
        return w;
    }
	
	/**
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
    public String copyToString() {
        StringBuilder buff = new StringBuilder();
        for (int y = height-1; y>=0; y--) {
            buff.append('|');
            for (int x=0; x<width; x++) {
                if (gridCopy[x][y]) buff.append('+');
                else buff.append(' ');
            }
            buff.append("|\n");
        }
        for (int x=0; x<width+2; x++) buff.append('-');
        return(buff.toString());
    }
}


