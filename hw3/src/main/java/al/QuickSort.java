package al;

public class QuickSort implements CharSorter {

    @Override
    public void sort(char[] a) {
        if (a == null || a.length < 2) return;
        quicksort(a, 0, a.length - 1);
    }

    private void quicksort(char[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, lo, hi);
        quicksort(a, lo, p - 1);
        quicksort(a, p + 1, hi);
    }

    // Lomuto partition
    private int partition(char[] a, int lo, int hi) {
        char pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi);
        return i;
    }

    private void swap(char[] a, int i, int j) {
        if (i == j) return;
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
