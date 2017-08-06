package com.khaled;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a least recent used cache.
 * O(1) insertion. O(1) deletion. O(1) lookup.
 *
 * Solution: - Insert every entry in a hash map and a doubly-linked linked list.
 *           - Every time you look up an entry insert it into the beginning of the list.
 *           - Every time you insert an entry insert, insert it into the beginning of the list.
 *             If the list is full, remove the tail of the list.
 */
public class LRU<K, V> {

    private static final int DEFAULT_SIZE = 1000;
    private int size;
    private Map <K, Entry> map = new HashMap<K, Entry>();
    private Entry first;
    private Entry last;

    private class Entry<K, V> {
        Entry prev = null;
        Entry next = null;
        K key;
        V val;

        Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRU(int size) {
        if (size < 0) {
            this.size = DEFAULT_SIZE;
        } else {
            this.size = size;
        }
    }

    public void put (K key, V val) {
        if (map.containsKey(key))
            return;
        Entry entry = new Entry(key, val);

        // If cache is full, remove last entry
        if (map.size() == size) {
            Entry lastEntry = map.remove(last.key);
            last = lastEntry.prev;
            if (lastEntry.prev == null) {
                // If last entry is also the first entry
                first = null;
            } else {
                lastEntry.prev.next = null;
            }
        }
        // Add entry to map
        map.put(key, entry);

        entry.next = first;
        if (first != null) {
            first.prev = entry;
        }
        first = entry;
        if (last == null) {
            last = entry;
        }
    }

    public V get(K key) {
        Entry<K, V> entry = map.get(key);
        if (entry == null) {
            return null;
        }

        if (entry.prev != null) {
            entry.prev.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.prev = entry.prev;
        } else {
            last = entry.prev;
        }
        // Move entry to first position in list
        first.prev = entry;
        entry.next = first;
        first = entry;

        return entry.val;
    }

    public static void main(String args[]) {
        // Note: test by setting breakpoints and debugging nodes.
        //       It's easier to debug by traversing the nodes starting from first and printing them out, but I'm too lazy rn.
        LRU<String, Integer> lru = new LRU<String, Integer>(5);
        lru.put("1",1);
        lru.put("2",2);
        lru.put("3",3);
        lru.put("4",4);
        lru.put("5",5);
        lru.get("1");
        lru.put("6", 6);
    }
}
