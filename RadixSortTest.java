import static org.junit.Assert.*;
import org.junit.Test;

public class RadixSortTest {

    @Test
    public void testSortNormalCase() {
        RadixSort sort = new RadixSort();
        Integer[] input = {170, 45, 75, 90, 802, 24, 2, 66};

        Integer[] result = sort.sort(input);

        assertArrayEquals(new Integer[]{2,24,45,66,75,90,170,802}, result);
    }

    @Test
    public void testDoesNotModifyOriginal() {
        RadixSort sort = new RadixSort();
        Integer[] input = {3, 1, 2};
        Integer[] originalCopy = input.clone();

        sort.sort(input);

        assertArrayEquals(originalCopy, input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumbersThrowException() {
        RadixSort sort = new RadixSort();
        sort.sort(new Integer[]{3, -1, 2});
    }
}
