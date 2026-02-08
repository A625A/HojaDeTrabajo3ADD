public class GnomeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public T[] sort(T[] array) {
        T[] arr = array.clone();
        int index = 0;

        while (index < arr.length) {
            if (index == 0) {
                index++;
            } else if (arr[index].compareTo(arr[index - 1]) >= 0) {
                index++;
            } else {
                T temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
        return arr;
    }
}
