/**
 * Created by khaledalturkestani on 7/11/17.
 */
public class MinHeap {

    private int[] arr;
    int heapSize = 0;

    /**
     * Constructor for an empty MinHeap
     *
     * @param initCapacity
     */
    public MinHeap(int initCapacity) {
        if (initCapacity <= 0)
            initCapacity = 16;
        arr = new int[initCapacity];
    }

    public int getMin() {
        if (heapSize == 0)
            return Integer.MAX_VALUE;
        return arr[0];
    }

    public int extractMin() {
        if (heapSize == 0)
            return Integer.MAX_VALUE;
        int min = arr[0];
        // Set Move the biggest value to root then heapify
        arr[0] = arr[heapSize-1];
        heapSize--;
        heapify(0);
        return min;
    }

    public void decreaseKey(int key, int new_val) {

    }

    public void deleteKey(int indx) {
        if (indx >= heapSize)
            return;

        arr[indx] = arr[heapSize-1];
        heapSize--;
        heapify(indx);
    }

    public void insertKey(int key) {

    }

    private void heapify(int indx) {

    }

    private int left(int indx) {
        return indx * 2 + 1;
    }

    private int right(int indx) {
        return indx * 2 + 2;
    }
}
