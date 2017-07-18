package com.khaled;

/**
 * Created by khaledalturkestani on 7/11/17.
 */
public class MinHeap {

    private int[] arr;
    int heapSize = 0;

    /**
     * Constructor for an empty com.khaled.MinHeap
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
        heapSize--;
        // Set Move the biggest value to root then heapify
        if (heapSize == 0) {
            return min;
        }
        arr[0] = arr[heapSize];
        heapify(0);
        return min;
    }

    public void decreaseKey(int indx, int new_val) {
        if (indx >= heapSize)
            throw new ArrayIndexOutOfBoundsException(indx);

        if (arr[indx] < new_val)
            throw new RuntimeException(new_val + " is greater than current value " + arr[indx]);

        arr[indx] = new_val;
        while (indx >= 0) {
            int parent = parent(indx);
            if (arr[parent] < arr[indx]) {
                break;
            }
            swap(parent, indx);
        }
    }

    public void deleteKey(int indx) {
        decreaseKey(indx, Integer.MIN_VALUE);
        extractMin();
    }

    /**
     * Returns index of key in the com.khaled.MinHeap array.
     *
     * @param key
     * @return
     */
    public int insertKey(int key) {
        if (heapSize == arr.length)
            return -1;

        heapSize++;
        int indx = heapSize - 1;
        arr[indx] = key;

        while (indx > 0) {
            int parent = parent(indx);
            if (arr[parent] <= arr[indx]) {
                break;
            }
            swap(indx, parent);
            indx = parent;
        }
        return indx;
    }

    private void heapify(int indx) {
        if (indx >= heapSize)
            return;

        int left = left(indx);
        int right = right(indx);
        int smaller = indx;
        if (left < heapSize && arr[left] < arr[smaller])
            smaller = left;
        if (right < heapSize && arr[right] < arr[smaller])
            smaller = right;
        if (smaller != indx) {
            swap(smaller, indx);
            heapify(smaller);
        }
    }

    public int size() {
        return heapSize;
    }

    private void swap(int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    private int parent(int indx) {
        return (indx - 1) / 2;
    }

    private int left(int indx) {
        return indx * 2 + 1;
    }

    private int right(int indx) {
        return indx * 2 + 2;
    }

    public static void main(String args[]) {
        MinHeap h = new MinHeap(16);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        int heapSize = h.size();
        for (int i = 0; i < heapSize; i++) {
            System.out.println(h.extractMin());
        }
    }
}
