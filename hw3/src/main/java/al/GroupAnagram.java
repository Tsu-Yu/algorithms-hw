package al;

import java.util.*;

public class GroupAnagram {

    // Default sorter = MergeSort
    // Question for TA: is it allowed to have two methods with same name but different parameters?
    public static List<List<String>> groupAnagram(List<String> strings) {
        return groupAnagram(strings, new MergeSort());
    }

    // Question for TA: can I add CharSorter as a parameter? then I can test both sorters
    // because Task description restricts us to use only one input which is List<String>
    public static List<List<String>> groupAnagram(List<String> strings, CharSorter sorter) {
        if (strings == null) return Collections.emptyList();
        Map<String, List<String>> buckets = new LinkedHashMap<>();
        for (String s : strings) {
            if (s == null) continue;
            String key = sortString(s, sorter);
            buckets.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(buckets.values());
    }

    // Helper method to sort characters in a string using the provided CharSorter
    public static String sortString(String str, CharSorter sorter) {
        if (str == null || str.length() < 2) return str == null ? null : str;
        char[] arr = str.toCharArray();
        sorter.sort(arr);
        return new String(arr);
    }
}
