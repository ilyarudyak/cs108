// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {

		return rowLength(ch) * columnLength(ch);
	}

    private int rowLength(char ch) {

        int first = -1;
        boolean isFirst = true;
        Queue<Integer> last = new PriorityQueue<>();

        for(int i=0; i<grid.length; i++) {

            char[] temp = Arrays.copyOf(grid[i], grid[i].length);
            Arrays.sort(temp);
            int index = Arrays.binarySearch(temp, ch);
//            System.out.println(index);

            if(index >= 0) {
                if(isFirst) { first = i; isFirst = false; }
                else { last.add(i); }
            }
        }

//        System.out.println(first);
//        System.out.println(last);

        // no such char
        if (first == -1) return 0;
        // only one element
        else if (first != -1 && last.isEmpty()) return 1;
        // standard case
        else return last.peek() - first + 1;

    }

    // Search for first and last column
    // contains this char. Then return difference + 1
    private int columnLength(char ch) {

        int firstColumn = -1, lastColumn = -1;
        for (int i=0; i<grid.length; i++) {

            // find first and last in given row i
            int tempFirst = -1, tempLast = -1;
            boolean isFirst = true;
            for (int j=0; j<grid[0].length; j++) {
                if (ch == grid[i][j]) {
                    if (isFirst) { tempFirst = j; isFirst = false; }
                    else { tempLast = j; }
                }
            }

            // if we have only one ch (or don't have any)
            if (tempLast == -1) { tempLast = tempFirst; }


            if (firstColumn == -1) { firstColumn = tempFirst; }
            else if (tempFirst != -1 && tempFirst < firstColumn) { firstColumn = tempFirst; }

            if (lastColumn == -1) { lastColumn = tempLast; }
            else if (tempLast != -1 && tempLast > lastColumn) { lastColumn = tempLast; }

//            System.out.println(firstColumn+" "+lastColumn);

        }
        if (firstColumn != -1 && lastColumn != -1)
            return lastColumn - firstColumn + 1;
        else return 0;
    }

	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {

        int count = 0;
        for (int i=1; i<grid.length-1; i++) {
            for (int j=1; j<grid[0].length-1; j++) {
                if (checkFirst(i, j) && checkSecond(i,j))
                    count++;
                }
            }
		return count;
	}

    // check first level in all directions from (i, j)
    private boolean checkFirst(int i, int j) {

        return  grid[i-1][j] == grid[i][j] &&
                grid[i+1][j] == grid[i][j] &&
                grid[i][j-1] == grid[i][j] &&
                grid[i][j+1] == grid[i][j];
    }

    private boolean checkSecond(int i, int j) {

        // if at least one character on the 2nd level == grid[i][j]
        // then we have to check other characters
        if (    (i-2>0              && grid[i-2][j] == grid[i][j]) ||
                (i+2<grid.length    && grid[i+2][j] == grid[i][j]) ||
                (j-2>0              && grid[i][j-2] == grid[i][j]) ||
                (j+2<grid[0].length && grid[i][j+2] == grid[i][j])
                ) {
            return checkLevelN(i, j, 2);
        }
        return true;
    }

    // we check only if one character on the Nth level == grid[i][j]
    private boolean checkLevelN(int i, int j, int N) {

        // if we can not go up to N level in ALL directions - false
        if (i-N<0 || i+N>=grid.length || j-N<0 || j+N>=grid[0].length) {
            return false;
        }

        return  grid[i-N][j] == grid[i][j] &&
                grid[i+N][j] == grid[i][j] &&
                grid[i][j-N] == grid[i][j] &&
                grid[i][j+N] == grid[i][j];
    }

    public static void main(String[] args) {

        char[][] grid = new char[][] {
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
                {'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
                {' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
                {' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
                {'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
                {' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '},

        };

        CharGrid cg = new CharGrid(grid);
        System.out.println(cg.countPlus());


    }

}














