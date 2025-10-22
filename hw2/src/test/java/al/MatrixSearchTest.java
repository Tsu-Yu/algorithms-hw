package al;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixSearchTest {

    private MatrixSearch solver;

    @BeforeEach
    void setUp() {
        solver = new MatrixSearch();
    }


    @Test
    void found_inMiddle() {
        int[][] m = {
                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17}
        };
        assertTrue(solver.searchMatrix(m, 9));
    }

    @Test
    void found_firstElement() {
        int[][] m = {
                {2, 4, 6},
                {8, 10, 12}
        };
        assertTrue(solver.searchMatrix(m, 2));
    }

    @Test
    void found_lastElement() {
        int[][] m = {
                {2, 4, 6},
                {8, 10, 12}
        };
        assertTrue(solver.searchMatrix(m, 12));
    }

    @Test
    void notFound_withinRange() {
        int[][] m = {
                {1, 2, 4},
                {5, 6, 7}
        };
        assertFalse(solver.searchMatrix(m, 3));
    }


    @Test
    void emptyMatrix_zeroRows() {
        int[][] m = new int[][]{}; // rows = 0
        assertFalse(solver.searchMatrix(m, 1));
    }

    @Test
    void emptyFirstRow_zeroCols() {
        int[][] m = new int[][]{ {} }; // rows = 1, cols = 0
        assertFalse(solver.searchMatrix(m, 1));
    }

    @Test
    void nullMatrix_returnsFalse_noThrow() {
        assertDoesNotThrow(() -> {
            boolean ans = solver.searchMatrix(null, 42);
            assertFalse(ans);
        });
    }

    @Test
    void singleElement_found() {
        int[][] m = { {7} };
        assertTrue(solver.searchMatrix(m, 7));
    }

    @Test
    void singleElement_notFound() {
        int[][] m = { {7} };
        assertFalse(solver.searchMatrix(m, 8));
    }

    @Test
    void singleRow_found_and_notFound() {
        int[][] m = { {1, 3, 5, 7, 9} };
        assertTrue(solver.searchMatrix(m, 7));
        assertFalse(solver.searchMatrix(m, 8));
    }

    @Test
    void singleColumn_found_and_notFound() {
        int[][] m = {
                { -10 },
                { -5 },
                { 0 },
                { 5 }
        };
        assertTrue(solver.searchMatrix(m, -5));
        assertFalse(solver.searchMatrix(m, 4));
    }

    @Test
    void handlesNegativeAndLargeValues() {
        int[][] m = {
                { -1000, -500, -1 },
                { 0, 1, 2 },
                { 100, 1000, 10000 }
        };
        assertTrue(solver.searchMatrix(m, -1));
        assertTrue(solver.searchMatrix(m, 10000));
        assertFalse(solver.searchMatrix(m, 3));
    }
}
