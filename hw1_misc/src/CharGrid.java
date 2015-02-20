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
		return 0; // TODO ADD YOUR CODE HERE
	}

    public static void main(String[] args) {

        char[][] grid = new char[][] {
                {'c', 'a', ' '},
                {'b', ' ', 'b'},
                {' ', ' ', 'a'}
        };

        CharGrid cg = new CharGrid(grid);

//        System.out.println(cg.charArea('c'));
//        System.out.println(cg.rowLength('c'));
//        System.out.println(cg.columnLength('c'));

//        System.out.println(Arrays.toString(grid[0]));
//        System.out.println(Arrays.binarySearch(cg.grid[0], 'c'));

    }

}














