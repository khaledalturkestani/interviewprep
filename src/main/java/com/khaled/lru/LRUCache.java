package com.khaled.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode: https://leetcode.com/problems/lru-cache/description/
 *
 */
public class LRUCache {
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private int size = 0;
    Node first = null;
    Node last = null;
    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            // Move to first of list & update first & last (if applicable)
            if (first != n) {
                makeFirst(n);
            }
            return n.val;
        }
        return -1;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            // Move to first of list & update first & last (if applicable)
            if (first != n) {
                makeFirst(n);
            }
            n.val = val;
            return;
        }
        if (size == capacity) {
            // Remove last node
            Node n = last;
            map.remove(n.key);
            last = n.prev;
            if (last != null)
                last.next = null;
            if (first == n)
                first = null;
        } else {
            size++;
        }
        // Create new node & add to map and beginning of list;
        Node n = new Node(key, val);
        n.next = first;
        if (first != null)
            first.prev = n;
        first = n;
        if (last == null)
            last = n;
        map.put(key, n);
    }

    private void makeFirst(Node n) {
        if (n.prev != null)
            n.prev.next = n.next;
        if (n.next != null)
            n.next.prev = n.prev;
        n.next = first;
        if (first != null)
            first.prev = n;
        first = n;
        if (size > 1 && last == n)
            last = n.prev;
        n.prev = null;
    }

    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(3);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.put(3, 3);
//        cache.put(4, 4);
//        int get = cache.get(4);
//        get = cache.get(3);
//        get = cache.get(2);
//        get = cache.get(1);
//        cache.put(5, 5);
//        get = cache.get(1);
//        get = cache.get(2);
//        get = cache.get(3);
//        get = cache.get(4);
//        get = cache.get(5);

LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        int get = cache.get(3);
        get = cache.get(2);
        cache.put(4, 3);
        get = cache.get(2);
        get = cache.get(3);
        get = cache.get(4);

    }
}
