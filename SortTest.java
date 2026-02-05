import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void main(String[] args) throws IOException {
        SortTest test = new SortTest();
        test.saveToFile(Path.of("sort_data.txt"));

        SortAlgorithm radix = new RadixSort();
        SortAlgorithm insertion = new InsertionSort();

        int[] radixSorted = radix.sort(test.getDataCopy());
        int[] insertionSorted = insertion.sort(test.getDataCopy());
        System.out.println("Generated: " + test.data.length + " numbers");
        System.out.println("RadixSort first/last: " + radixSorted[0] + " / " + radixSorted[radixSorted.length - 1]);
        System.out.println("InsertionSort first/last: " + insertionSorted[0] + " / " + insertionSorted[insertionSorted.length - 1]);
    }
}
