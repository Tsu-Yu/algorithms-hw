package al;

public class MergeSort implements CharSorter {

    // aux is allocated once to avoid repeated allocations
    @Override
    public void sort(char[] a) {
        if (a == null || a.length < 2) return;
        char[] aux = new char[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    private void mergeSort(char[] a, char[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(char[] a, char[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)              a[k] = aux[j++];
            else if (j > hi)          a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else                      a[k] = aux[i++];
        }
    }
}
