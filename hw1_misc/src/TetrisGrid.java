//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {

    private boolean[][] grid;
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
        this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {

        // we use while - after clearance
        // row can be full again and should be cleared
        int y=0;
        while (y<grid[0].length){
            if (isFull(y)) { clearRow(y); }
            else { y++; }
        }
	}

    /**
     * Check if line is full
     * @return true if line is full
     * */
    private boolean isFull(int y) {

        for (int x=0; x<grid.length; x++) {

            // found empty cell
            if (grid[x][y] == false) { return false; }
        }
        return true;
    }

    /**
     * Move all above rows 1 row down and
     * add empty row above
     * */
    private void clearRow (int y0) {

        for (int x=0; x<grid.length; x++) {
            for(int y=y0; y<grid[0].length-1; y++) {
                grid[x][y] = grid[x][y + 1];
            }
            grid[x][grid[0].length-1] = false;
        }


    }
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int y=grid[0].length-1; y>=0; y--) {
            for(int x=0; x<grid.length; x++) {
                sb.append(grid[x][y] == true ? "x" : "o");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        boolean[][] grid =
                {
                        {true, true, true, false, true, false},
                        {false, true, true, true, true, false},
                        {true, true, true, false, true, true}
                };

        TetrisGrid tg = new TetrisGrid(grid);
        System.out.println(tg.toString());

//        System.out.println(tg.isFull(0));
//        System.out.println(tg.isFull(1));
//        System.out.println(tg.isFull(2));

        tg.clearRows();
        System.out.println(tg.toString());

    }
}














