package al;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class RangeSearchTest {
    RangeSearch rs = new RangeSearch();

    @Test
    void example1() {
        assertArrayEquals(new int[]{4, 5},
            rs.searchRange(new int[]{4,9,10,13,17,17,19,21}, 17));
    }

    @Test
    void example2() {
        assertArrayEquals(new int[]{-1, -1},
            rs.searchRange(new int[]{2,4,6,8,10,14,16}, 12));
    }

    @Test
    void example3() {
        assertArrayEquals(new int[]{-1, -1},
            rs.searchRange(new int[]{}, 0));
    }

    // 其他邊界/強化測試
    @Test
    void singleHit() {
        assertArrayEquals(new int[]{0, 0},
            rs.searchRange(new int[]{7}, 7));
    }

    @Test
    void singleMiss() {
        assertArrayEquals(new int[]{-1, -1},
            rs.searchRange(new int[]{7}, 8));
    }

    @Test
    void allSameAreTarget() {
        assertArrayEquals(new int[]{0, 4},
            rs.searchRange(new int[]{5,5,5,5,5}, 5));
    }

    @Test
    void targetAtHead() {
        assertArrayEquals(new int[]{0, 1},
            rs.searchRange(new int[]{3,3,4,5,9}, 3));
    }

    @Test
    void targetAtTail() {
        assertArrayEquals(new int[]{3, 4},
            rs.searchRange(new int[]{1,2,3,6,6}, 6));
    }

    @Test
    void duplicatesSparse() {
        assertArrayEquals(new int[]{2, 4},
            rs.searchRange(new int[]{1,2,4,4,4,5,9}, 4));
    }

    @Test
    void notFoundBetweenValues() {
        assertArrayEquals(new int[]{-1, -1},
            rs.searchRange(new int[]{1,3,5,7}, 6));
    }
}
