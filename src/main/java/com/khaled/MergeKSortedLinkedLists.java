package com.khaled;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeKSortedLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode merge(ListNode n1, ListNode n2) {
        if (n1 == null && n2 == null)
            return null;
        if (n1 == null)
            return n2;
        if (n2 == null)
            return n1;
        ListNode s1 = n1;
        ListNode s2 = n2;
        if (s1.val > s2.val) {
            s1 = n2;
            s2 = n1;
        }

        while (s1.next != null) {
            while (s2 != null && s2.val <= s1.next.val) {
                ListNode temp1 = s1.next;
                ListNode temp2 = s2.next;
                s1.next = s2;
                s2.next = temp1;
                s1 = s2;
                s2 = temp2;
            }
            if (s1.next!= null)
                s1 = s1.next;
        }

        s1.next = s2;
        return (n1.val > n2.val) ? n2 : n1;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        int size = lists.length;
        ListNode[] cur = lists;
        while (size > 1) {
            int newSize = (size % 2 != 0) ? size/2+1 : size/2;
            ListNode[] merged = new ListNode[newSize];
            for (int i = 0; i < size-1; i +=2 )
                merged[i/2] = merge(cur[i], cur[i+1]);
            if (size % 2 != 0)
                merged[newSize-1] = cur[size-1];
            size = newSize;
            cur = merged;
        }
        return cur[0];
    }

    public static void printList(ListNode n) {
        while (n != null) {
            System.out.print(n.val + ", ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //[[],[-1,5],[1,4,6],[4,5,6]]
        //[[-10,-9,-9,-3,-1,-1,0],[-5],[4],[-8],[],[-9,-6,-5,-4,-2,2,3],[-3,-3,-2,-1,0]]
        ListNode a1 = new ListNode(-10);
        ListNode a2 = new ListNode(-9);
        ListNode a3 = new ListNode(-9);
        ListNode a4 = new ListNode(-3);
        ListNode a5 = new ListNode(-1);
        ListNode a6 = new ListNode(-1);
        ListNode a7 = new ListNode(0);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;
        ListNode b1 = new ListNode(-5);
        ListNode c1 = new ListNode(4);
        ListNode d1 = new ListNode(-8);
        ListNode e1 = new ListNode(-9);
        ListNode e2 = new ListNode(-6);
        ListNode e3 = new ListNode(-5);
        ListNode e4 = new ListNode(-4);
        ListNode e5 = new ListNode(-2);
        ListNode e6 = new ListNode(2);
        ListNode e7 = new ListNode(3);
        e1.next = e2;
        e2.next = e3;
        e3.next = e4;
        e4.next = e5;
        e5.next = e6;
        e6.next = e7;
        ListNode f1 = new ListNode(-3);
        ListNode f2 = new ListNode(-3);
        ListNode f3 = new ListNode(-2);
        ListNode f4 = new ListNode(-1);
        ListNode f5 = new ListNode(0);
        f1.next = f2;
        f2.next = f3;
        f3.next = f4;
        f4.next = f5;

        ListNode[] list = new ListNode[7];
        list[0] = a1;
        list[1] = b1;
        list[2] = c1;
        list[3] = d1;
        list[4] = null;
        list[5] = e1;
        list[6] = f1;
        ListNode merged = mergeKLists(list);
        printList(merged);
    }
}
