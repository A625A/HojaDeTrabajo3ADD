import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class SortTest {
    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 3000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 3000;

    private final int[] data;

    public SortTest() {
        this(System.nanoTime());
    }

    public SortTest(long seed) {
        this.data = generateRandomData(seed);
    }

    public int[] getDataCopy() {
        return data.clone();
    }

    public void saveToFile(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(data[i]);
        }
        Files.writeString(path, sb.toString());
    }

    private static int[] generateRandomData(long seed) {
        Random random = new Random(seed);
        int size = MIN_COUNT + random.nextInt(MAX_COUNT - MIN_COUNT + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE + 1);
        }
        return arr;
    }

    private static String firstFiveAndLast(int[] arr) {
        int count = Math.min(5, arr.length);
        StringBuilder sb = new StringBuilder();
        sb.append("first5: ");
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(arr[i]);
        }
        sb.append(" | last: ").append(arr[arr.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        SortTest test = new SortTest();
        test.saveToFile(Path.of("sort_data.txt"));

        SortAlgorithm radix = new RadixSort();
        SortAlgorithm insertion = new InsertionSort();

        int[] unsorted = test.getDataCopy();

        long radixStart = System.nanoTime();
        int[] radixSorted = radix.sort(unsorted);
        long radixElapsedNs = System.nanoTime() - radixStart;

        long insertionStart = System.nanoTime();
        int[] insertionSorted = insertion.sort(unsorted);
        long insertionElapsedNs = System.nanoTime() - insertionStart;

        long radixSortedStart = System.nanoTime();
        int[] radixSortedAgain = radix.sort(radixSorted);
        long radixSortedElapsedNs = System.nanoTime() - radixSortedStart;

        long insertionSortedStart = System.nanoTime();
        int[] insertionSortedAgain = insertion.sort(radixSorted);
        long insertionSortedElapsedNs = System.nanoTime() - insertionSortedStart;

        boolean sameResult = Arrays.equals(radixSorted, insertionSorted);
        boolean sameSortedAgain = Arrays.equals(radixSortedAgain, insertionSortedAgain);
        double radixMs = radixElapsedNs / 1_000_000.0;
        double insertionMs = insertionElapsedNs / 1_000_000.0;
        double radixSortedMs = radixSortedElapsedNs / 1_000_000.0;
        double insertionSortedMs = insertionSortedElapsedNs / 1_000_000.0;
        System.out.println("Generated: " + test.data.length + " numbers");
        System.out.println("Unsorted " + firstFiveAndLast(unsorted));
        System.out.println("RadixSort first/last: " + radixSorted[0] + " / " + radixSorted[radixSorted.length - 1]);
        System.out.println("InsertionSort first/last: " + insertionSorted[0] + " / " + insertionSorted[insertionSorted.length - 1]);
        System.out.println("Same result: " + sameResult);
        System.out.println("Same result (sorted input): " + sameSortedAgain);
        System.out.printf("RadixSort time (unsorted): %.3f ms%n", radixMs);
        System.out.printf("InsertionSort time (unsorted): %.3f ms%n", insertionMs);
        System.out.printf("RadixSort time (sorted): %.3f ms%n", radixSortedMs);
        System.out.printf("InsertionSort time (sorted): %.3f ms%n", insertionSortedMs);
    }
}
