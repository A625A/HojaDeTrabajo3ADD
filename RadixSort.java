import java.util.Arrays;

public class RadixSort implements SortAlgorithm<Integer> {
    @Override
    public Integer[] sort(Integer[] array) {
        Integer[] input = array.clone();
        if (input.length == 0) return input;

        int[] arr = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int v = input[i];
            if (v < 0) {
                throw new IllegalArgumentException(
                    "RadixSort only supports non-negative integers."
                );
            }
            arr[i] = v;
        }

        radixSort(arr);

        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
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
