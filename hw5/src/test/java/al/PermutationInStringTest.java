package al;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PermutationInStringTest {
    @Test
    void exampleTrue() {
        assertTrue(new PermutationInString().checkInclusion("ab", "eidbaooo"));
    }

    @Test
    void exampleFalse() {
        assertFalse(new PermutationInString().checkInclusion("ab", "eidboaoo"));
    }

    @Test
    void sameStrings() {
        assertTrue(new PermutationInString().checkInclusion("abc", "abc"));
    }

    @Test
    void repeatedChars() {
        assertTrue(new PermutationInString().checkInclusion("aab", "aaab"));
        assertFalse(new PermutationInString().checkInclusion("aab", "ab"));
    }

    @Test
    void longerS1ThanS2() {
        assertFalse(new PermutationInString().checkInclusion("abcd", "abc"));
    }

    @Test
    void nonLowercaseAscii() {
        assertTrue(new PermutationInString().checkInclusion("A!", "zz!Ay"));
        assertFalse(new PermutationInString().checkInclusion("A!", "zzy"));
    }
}
