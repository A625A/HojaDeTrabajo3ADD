import static org.junit.Assert.*;
import org.junit.Test;

public class QuickSortTest {

    @Test
    public void testSortNormalCase() {
        SortAlgorithm<Integer> sort = new QuickSort<>();
        Integer[] input = {4, 10, 3, 5, 1};

        Integer[] result = sort.sort(input);

        assertArrayEquals(new Integer[]{1,3,4,5,10}, result);
    }

    @Test
    public void testDoesNotModifyOriginal() {
        SortAlgorithm<Integer> sort = new QuickSort<>();
        Integer[] input = {8, 2, 6};
        Integer[] originalCopy = input.clone();

        sort.sort(input);

        assertArrayEquals(originalCopy, input);
    }
}
