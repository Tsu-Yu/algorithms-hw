package al;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EightQueensMinMovesTest {

    @Test
    void example1_increasing12345678() {
        int moves = new EightQueensMinMoves().minMoves(new int[]{1,2,3,4,5,6,7,8});
        assertEquals(7, moves);
    }

    @Test
    void example2_allOnes() {
        int moves = new EightQueensMinMoves().minMoves(new int[]{1,1,1,1,1,1,1,1});
        assertEquals(7, moves);
    }

    @Test
    void alreadyASolution_zeroMoves() {
        // One classic valid solution: [1,5,8,6,3,7,2,4]
        int moves = new EightQueensMinMoves().minMoves(new int[]{1,5,8,6,3,7,2,4});
        assertEquals(0, moves);
    }

    @Test
    void offByTwo() {
        int moves = new EightQueensMinMoves().minMoves(new int[]{1,5,8,6,3,7,2,5}); // last col off by 1
        assertTrue(moves >= 1);
    }

    @Test
    void randomConfig() {
        int moves = new EightQueensMinMoves().minMoves(new int[]{4,7,3,8,6,2,5,1});
        assertTrue(moves >= 0 && moves <= 8);
    }

    @Test
    void invalidInputThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new EightQueensMinMoves().minMoves(new int[]{1,2,3}));
    }
}
