public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public T[] sort(T[] array) {
        T[] arr = array.clone();
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Object[] L = new Object[n1];
        Object[] R = new Object[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            @SuppressWarnings("unchecked")
            T leftVal = (T) L[i];
            @SuppressWarnings("unchecked")
            T rightVal = (T) R[j];

            if (leftVal.compareTo(rightVal) <= 0) {
                arr[k++] = leftVal;
                i++;
            } else {
                arr[k++] = rightVal;
                j++;
            }
        }

        while (i < n1) {
            @SuppressWarnings("unchecked")
            T leftVal = (T) L[i++];
            arr[k++] = leftVal;
        }

        while (j < n2) {
            @SuppressWarnings("unchecked")
            T rightVal = (T) R[j++];
            arr[k++] = rightVal;
        }
    }
}
