// Board.java
package tetris;

import com.google.common.primitives.Ints;

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

	private boolean DEBUG = true;
	boolean committed;

    // how many filled spots
    // there are in each row
    int[] widths;

    // how many filled spots
    // there are in each column
    int[] heights;
	

	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		committed = true;

        widths = new int[height];
        heights = new int[width];

		
		// TODO YOUR CODE HERE
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
     */
    public boolean getGrid(int x, int y) {

        if (    x < 0 || x >= width ||
                y < 0 || y >= height ) {
            return true;
        }

        // spot is filled if grid == true
        if (grid[x][y] == true) { return true; }

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
		return 0; // TODO YOUR CODE HERE
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
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
			
        for (TPoint p: piece.getBody()) {

            if (isOutOfBounds(x + p.x, y + p.y)) { return PLACE_OUT_BOUNDS; }
            else if (getGrid(x + p.x, y + p.y)) { return PLACE_BAD; }
            else {
                grid[x + p.x][y + p.y] = true;

                // update widths and heights
                widths[y + p.y]++;
                if (y + p.y + 1 > heights[x + p.x]) {
                    heights[x + p.x] = y + p.y + 1;
                }
            }
        }

        if (getRowFilled() != -1) { return PLACE_ROW_FILLED; }
		return PLACE_OK;
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

		int rowsCleared = 0;
        int indexRowFilled = getRowFilled();
        while (true) {
            clearRow(indexRowFilled);
            rowsCleared++;
            indexRowFilled = getRowFilled();
//            System.out.println(indexRowFilled);
            if (indexRowFilled == -1) { break; }
        }
//		sanityCheck();
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
		// TODO YOUR CODE HERE
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
            // TODO YOUR CODE HERE
        }
    }
	
	/*
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
}


