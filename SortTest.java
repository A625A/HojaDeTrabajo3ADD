import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class SortTest {

    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 3000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 3000;

    private final Integer[] data;

    public SortTest(long seed) {
        int size = MIN_COUNT + new Random(seed).nextInt(MAX_COUNT - MIN_COUNT + 1);
        this.data = generateRandomData(seed, size);
    }

    // Genera los números aleatorios
    private static Integer[] generateRandomData(long seed, int size) {
        Random random = new Random(seed);
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE + 1);
        }
        return arr;
    }

    // Guarda los datos en archivo
    public void saveToFile(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(data[i]);
        }
        Files.writeString(path, sb.toString());
    }

    // Lee los datos desde archivo
    public static Integer[] readFromFile(Path path) throws IOException {
        String content = Files.readString(path).trim();
        if (content.isEmpty()) return new Integer[0];

        String[] parts = content.split(",");
        Integer[] arr = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {

        // Se genera el archivo con entre 10 y 3000 números
        SortTest generator = new SortTest(System.nanoTime());
        Path filePath = Path.of("sort_data.txt");
        generator.saveToFile(filePath);

        // Se leen los datos desde el archivo
        Integer[] baseData = readFromFile(filePath);

        // Algoritmos
        SortAlgorithm<Integer> insertion = new InsertionSort<>();
        SortAlgorithm<Integer> gnome = new GnomeSort<>();
        SortAlgorithm<Integer> merge = new MergeSort<>();
        SortAlgorithm<Integer> quick = new QuickSort<>();
        RadixSort radix = new RadixSort();

        // Ejecutar con datos DESORDENADOS
        Integer[] insertionData = baseData.clone();
        Integer[] gnomeData = baseData.clone();
        Integer[] mergeData = baseData.clone();
        Integer[] quickData = baseData.clone();
        Integer[] radixData = baseData.clone();

        long t = System.nanoTime();
        Integer[] insertionSorted = insertion.sort(insertionData);
        long insertionUnsortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        Integer[] gnomeSorted = gnome.sort(gnomeData);
        long gnomeUnsortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        Integer[] mergeSorted = merge.sort(mergeData);
        long mergeUnsortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        Integer[] quickSorted = quick.sort(quickData);
        long quickUnsortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        Integer[] radixSorted = radix.sort(radixData);
        long radixUnsortedTime = System.nanoTime() - t;

        // Ejecutar con datos YA ORDENADOS
        t = System.nanoTime();
        insertion.sort(insertionSorted.clone());
        long insertionSortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        gnome.sort(gnomeSorted.clone());
        long gnomeSortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        merge.sort(mergeSorted.clone());
        long mergeSortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        quick.sort(quickSorted.clone());
        long quickSortedTime = System.nanoTime() - t;

        t = System.nanoTime();
        radix.sort(radixSorted.clone());
        long radixSortedTime = System.nanoTime() - t;

        //Mostrar resultados
        System.out.println("Cantidad de números: " + baseData.length);

        System.out.printf("InsertionSort  -> desordenado: %d ns | ordenado: %d ns%n", insertionUnsortedTime, insertionSortedTime);
        System.out.printf("GnomeSort      -> desordenado: %d ns | ordenado: %d ns%n", gnomeUnsortedTime, gnomeSortedTime);
        System.out.printf("MergeSort      -> desordenado: %d ns | ordenado: %d ns%n", mergeUnsortedTime, mergeSortedTime);
        System.out.printf("QuickSort      -> desordenado: %d ns | ordenado: %d ns%n", quickUnsortedTime, quickSortedTime);
        System.out.printf("RadixSort      -> desordenado: %d ns | ordenado: %d ns%n", radixUnsortedTime, radixSortedTime);
    }
}
