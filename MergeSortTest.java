import static org.junit.Assert.*;
import org.junit.Test;

public class MergeSortTest {

    @Test
    public void testSortNormalCase() {
        SortAlgorithm<Integer> sort = new MergeSort<>();
        Integer[] input = {10, 7, 8, 9, 1, 5};

        Integer[] result = sort.sort(input);

        assertArrayEquals(new Integer[]{1,5,7,8,9,10}, result);
    }

    @Test
    public void testDoesNotModifyOriginal() {
        SortAlgorithm<Integer> sort = new MergeSort<>();
        Integer[] input = {4, 1, 3};
        Integer[] originalCopy = input.clone();

        sort.sort(input);

        assertArrayEquals(originalCopy, input);
    }
}
