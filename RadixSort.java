import java.util.Arrays;

public class RadixSort implements SortAlgorithm {
    @Override
    public int[] sort(int[] array) {
        int[] arr = array.clone(); 
        if (arr.length == 0) return arr;

        for (int v : arr) {
            if (v < 0) {
                throw new IllegalArgumentException(
                    "RadixSort only supports non-negative integers."
                );
            }
        }

        radixSort(arr);
        return arr;
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    private static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }
}
