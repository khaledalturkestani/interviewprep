package com.khaled;

/**
 * Created by khaledalturkestani on 7/12/17.
 */
public class MaxHeap {

    private int[] arr;
    private int heapSize = 0;

    public MaxHeap(int capacity) {
        if (capacity <= 0)
            capacity = 16;

        arr = new int[capacity];
    }

    public int size() {
        return heapSize;
    }

    /**
      * @param key
      * @return - Index of key in the com.khaled.MaxHeap array. -1 if array is full.
      */
    public int insertKey(int key) {
        if (heapSize == arr.length)
            return -1;

        heapSize++;
        int indx = heapSize - 1;
        arr[indx] = key;
        while (indx > 0) {
            int parent = parent(indx);
            if (arr[parent] > arr[indx]) {
                break;
            }
            swap(indx, parent);
            indx = parent;
        }
        return indx;
    }

    public void deleteKey(int indx) {
        increaseKey(indx, Integer.MAX_VALUE);
        extractMax();
    }

    public int getMax() {
        if (heapSize == 0)
            return Integer.MIN_VALUE;

        return arr[0];
    }

    /**
     *
     * @return - Max value in com.khaled.MaxHeap. MIN_VALUE if com.khaled.MaxHeap is empty.
     */
    public int extractMax() {
        if (heapSize == 0)
            return Integer.MIN_VALUE;

        int max = arr[0];
        heapSize--;
        if (heapSize == 0) {
            return max;
        }

        arr[0] = arr[heapSize];
        heapify(0);
        return max;
    }

    public void increaseKey(int indx, int newValue) {
        if (indx >= heapSize) {
            throw new ArrayIndexOutOfBoundsException(indx);
        }

        arr[indx] = newValue;

        while (indx > 0) {
            int parent = parent(indx);
            if (arr[parent] > arr[indx]) {
                break;
            }
            swap(indx, parent);
            indx = parent;
        }
    }

    private void heapify(int indx) {
        if (indx > heapSize)
            return;

        int left = left(indx);
        int right = right(indx);
        int largest = indx;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != indx) {
            swap(largest, indx);
            heapify(largest);
        }
    }

    private void swap(int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private int parent(int indx) {
        return (indx - 1) /  2;
    }

    private int left(int indx) {
        return 2 * indx + 1;
    }

    private int right(int indx) {
        return 2 * indx + 2;
    }

    public static void main(String args[]) {
        MaxHeap h = new MaxHeap(16);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        int heapSize = h.size();
        for (int i = 0; i < heapSize; i++) {
            System.out.println(h.extractMax());
        }
    }
}


