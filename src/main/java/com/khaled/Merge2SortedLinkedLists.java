package com.khaled;

/**
 * Created by khaledalturkestani on 12/6/17.
 */
public class Merge2SortedLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode merge(ListNode n1, ListNode n2) {
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

    public static void printList(ListNode n) {
        while (n != null) {
            System.out.print(n.val + ", ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1->2->3->6->7
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        // 1->1->2->2->4->5->8
        ListNode m1 = new ListNode(1);
        ListNode m2 = new ListNode(1);
        ListNode m3 = new ListNode(2);
        ListNode m4 = new ListNode(2);
        ListNode m5 = new ListNode(4);
        ListNode m6 = new ListNode(5);
        ListNode m7 = new ListNode(8);
        m1.next = m2;
        m2.next = m3;
        m3.next = m4;
        m4.next = m5;
        m5.next = m6;
        m6.next = m7;

        ListNode merged = merge(n1, m1);
        printList(merged);
    }
}
