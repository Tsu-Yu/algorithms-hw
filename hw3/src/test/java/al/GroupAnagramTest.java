package al;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GroupAnagramTest {

    @Test
    @DisplayName("sortString: MergeSort vs QuickSort produce identical results")
    void sortersProduceSameResult() {
        String[] samples = {"mango", "ogman", "bucket", "rat", "tar", "a", "", "zzYYxx", "ABab"};
        MergeSort ms = new MergeSort();
        QuickSort qs = new QuickSort();
        for (String s : samples) {
            String m = GroupAnagram.sortString(s, ms);
            String q = GroupAnagram.sortString(s, qs);
            assertEquals(m, q, "Mismatch on: " + s);
        }
    }

    @Test
    @DisplayName("groupAnagram: matches the example output (order-insensitive)")
    void groupsMatchExample() {
        List<String> input = Arrays.asList("bucket","rat","mango","tango","ogtan","tar");
        List<List<String>> result = GroupAnagram.groupAnagram(input, new MergeSort());

        Set<Set<String>> asSets = result.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());

        Set<Set<String>> expected = new HashSet<>();
        expected.add(new HashSet<>(Collections.singletonList("bucket")));
        expected.add(new HashSet<>(Arrays.asList("rat","tar")));
        expected.add(new HashSet<>(Collections.singletonList("mango")));
        expected.add(new HashSet<>(Arrays.asList("tango","ogtan")));

        assertEquals(expected, asSets);
    }

    @Test
    @DisplayName("groupAnagram: handles empty strings and duplicates")
    void handlesEdgeCases() {
        List<String> input = Arrays.asList("", "", "a", "a", "b", "ba", "ab");
        List<List<String>> result = GroupAnagram.groupAnagram(input, new QuickSort());

        Map<String, Set<String>> signature = new HashMap<>();
        for (List<String> bucket : result) {
            String key = bucket.get(0) == null ? "" : GroupAnagram.sortString(bucket.get(0), new QuickSort());
            signature.put(key, new HashSet<>(bucket));
        }

        assertTrue(signature.containsKey(""), "Empty-string bucket missing");
        assertTrue(signature.get("").contains(""), "Empty-string not grouped");
        assertEquals(new HashSet<>(Collections.singletonList("a")), signature.get("a"));
        assertEquals(new HashSet<>(Collections.singletonList("b")), signature.get("b"));
        assertEquals(new HashSet<>(new HashSet<>(Arrays.asList("ba","ab"))), signature.get("ab"));
    }

    @Test
    @DisplayName("Performance comparison on random data (checks equality only)")
    void performanceComparison() {
        Random rnd = new Random(42);
        int n = 8_000; // keep this reasonable for CI
        List<String> words = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int len = 3 + rnd.nextInt(7); // 3..9
            StringBuilder sb = new StringBuilder(len);
            for (int j = 0; j < len; j++) sb.append((char) ('a' + rnd.nextInt(26)));
            words.add(sb.toString());
        }

        long t1 = System.nanoTime();
        List<List<String>> g1 = GroupAnagram.groupAnagram(words, new MergeSort());
        long t2 = System.nanoTime();
        List<List<String>> g2 = GroupAnagram.groupAnagram(words, new QuickSort());
        long t3 = System.nanoTime();

        Function<List<List<String>>, List<String>> canon = groups -> groups.stream()
                .map(bucket -> {
                    List<String> b = new ArrayList<>(bucket);
                    Collections.sort(b);
                    return String.join(",", b);
                })
                .sorted()
                .collect(Collectors.toList());

        List<String> c1 = canon.apply(g1);
        List<String> c2 = canon.apply(g2);
        assertEquals(c1, c2, "Grouping differs between sorters");

        System.out.println("MergeSort time (ms): " + (t2 - t1) / 1_000_000.0);
        System.out.println("QuickSort time (ms): " + (t3 - t2) / 1_000_000.0);
    }
}
