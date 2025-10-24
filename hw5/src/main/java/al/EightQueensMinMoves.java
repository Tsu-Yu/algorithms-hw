package al;

import java.util.ArrayList;
import java.util.List;

public class EightQueensMinMoves {

    private static final List<int[]> SOLUTIONS = new ArrayList<>();

    // when someone loads this class(new EightQueensMinMoves()), this block runs once
    static {
        // precompute all 92 solutions to 8-queens problem
        // used row is 9 because rows' number are 1..8
        backtrack(0, new int[8], new boolean[9], new boolean[15], new boolean[15]);
    }

    private static void backtrack(int col, int[] rows, boolean[] usedRow, boolean[] diag1, boolean[] diag2) {
        // col: current column (0..7)
        // if all columns are filled, store the solution
        if (col == 8) {
            int[] sol = rows.clone(); // store a copy of the solution
            SOLUTIONS.add(sol);
            return;
        }

        // try placing a queen in each row of the current column
        for (int r = 1; r <= 8; r++) {
            int d1 = (col - (r - 1)) + 7;  // r-1 to convert to 0-based index
            //         col →   0   1   2   3   4   5   6   7
            // r=1 (row0=0)   [7] [8] [9] [10][11][12][13][14]
            // r=2 (row0=1)   [6] [7] [8] [9] [10][11][12][13]
            // r=3 (row0=2)   [5] [6] [7] [8] [9] [10][11][12]
            // r=4 (row0=3)   [4] [5] [6] [7] [8] [9] [10][11]
            // r=5 (row0=4)   [3] [4] [5] [6] [7] [8] [9] [10]
            // r=6 (row0=5)   [2] [3] [4] [5] [6] [7] [8] [9]
            // r=7 (row0=6)   [1] [2] [3] [4] [5] [6] [7] [8]
            // r=8 (row0=7)   [0] [1] [2] [3] [4] [5] [6] [7]
            int d2 = col + (r - 1);
            //         col →   0   1   2   3   4   5   6   7
            // r=1 (row0=0)   [0] [1] [2] [3] [4] [5] [6] [7]
            // r=2 (row0=1)   [1] [2] [3] [4] [5] [6] [7] [8]
            // r=3 (row0=2)   [2] [3] [4] [5] [6] [7] [8] [9]
            // r=4 (row0=3)   [3] [4] [5] [6] [7] [8] [9] [10]
            // r=5 (row0=4)   [4] [5] [6] [7] [8] [9] [10][11]
            // r=6 (row0=5)   [5] [6] [7] [8] [9] [10][11][12]
            // r=7 (row0=6)   [6] [7] [8] [9] [10][11][12][13]
            // r=8 (row0=7)   [7] [8] [9] [10][11][12][13][14]

            // Safe to place a queen here if:
            // 1) this row is not used, and
            // 2) the '\' diagonal is not used, and
            // 3) the '/' diagonal is not used. 
            if (!usedRow[r] && !diag1[d1] && !diag2[d2]) {
                rows[col] = r;
                usedRow[r] = true;
                diag1[d1] = true;
                diag2[d2] = true;

                // recurse to place queen in next column
                backtrack(col + 1, rows, usedRow, diag1, diag2);

                // Backtrack (undo the choice) so we can try the next row:
                // restore the occupancy state for row and diagonals
                usedRow[r] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }

    public int minMoves(int[] colsToRows) {
        if (colsToRows == null || colsToRows.length != 8)
            throw new IllegalArgumentException("Input must have 8 integers (rows 1..8 for columns 1..8).");

        // worst case: all 8 queens need to be moved
        int best = 8; 

        // compare against all precomputed solutions
        for (int[] sol : SOLUTIONS) {
            // count differences: it's the number of moves needed
            int diff = 0;
            // count how many queens are in different rows compared to this solution
            for (int c = 0; c < 8; c++) {
                if (colsToRows[c] != sol[c]) diff++;
                if (diff >= best) break; // more than 8, more worst
            }
            // pick min solution
            best = Math.min(best, diff);
        }
        return best;
    }
}
