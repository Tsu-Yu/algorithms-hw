package al;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // if s1 is longer than s2, no permutation is possible
        if (n > m) return false;

        // frequency arrays for characters (ASCII assumed)
        int[] need = new int[128];
        int[] win  = new int[128];

        // initialize frequency for s1 and the first window in s2
        for (int i = 0; i < n; i++) {
            need[s1.charAt(i)]++;
            win[s2.charAt(i)]++;
        }

        // quick check for the first window
        if (same(need, win)) return true;

        // slide the window over s2
        for (int r = n; r < m; r++) {
            // include s2[r], exclude s2[r - n]
            win[s2.charAt(r)]++;
            win[s2.charAt(r - n)]--;

            // check if current window matches the needed frequency
            if (same(need, win)) return true;
        }
        
        return false;
    }

    private boolean same(int[] a, int[] b) {
        for (int i = 0; i < 128; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}
