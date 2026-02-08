import static org.junit.Assert.*;
import org.junit.Test;

public class GnomeSortTest {

    @Test
    public void testSortNormalCase() {
        SortAlgorithm<Integer> sort = new GnomeSort<>();
        Integer[] input = {9, 4, 6, 2, 1};

        Integer[] result = sort.sort(input);

        assertArrayEquals(new Integer[]{1,2,4,6,9}, result);
    }

    @Test
    public void testDoesNotModifyOriginal() {
        SortAlgorithm<Integer> sort = new GnomeSort<>();
        Integer[] input = {3, 1, 2};
        Integer[] originalCopy = input.clone();

        sort.sort(input);

        assertArrayEquals(originalCopy, input);
    }
}
