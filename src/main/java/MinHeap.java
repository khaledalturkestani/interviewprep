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

    /**
     * Constructor to turn an array to MinHeap
     *
     * @return
     */
    public MinHeap(int[] arr) {

    }


    public int getMin() {
        if (heapSize == 0)
            return Integer.MAX_VALUE;
        return arr[0];
    }

    public int extractMin() {

    }

    public void decreaseKey(int key, int new_val) {

    }

    public void deleteKey(int key) {

    }

    public void insertKey(int key) {

    }

    private int left(int idx) {
        return idx * 2 + 1;
    }

    private int right(int idx) {
        return idx * 2 + 2;
    }
}
