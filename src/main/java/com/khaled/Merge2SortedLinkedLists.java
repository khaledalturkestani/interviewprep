package com.khaled;

/**
 * Created by khaledalturkestani on 12/6/17.
 */
public class Merge2SortedLinkedLists {

    public static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public static Node merge(Node n1, Node n2) {
        Node s1 = n1;
        Node s2 = n2;
        if (s1.val > s2.val) {
            s1 = n2;
            s2 = n1;
        }

        while (s1.next != null) {
            while (s2 != null && s2.val <= s1.next.val) {
                Node temp1 = s1.next;
                Node temp2 = s2.next;
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

    public static void printList(Node n) {
        while (n != null) {
            System.out.print(n.val + ", ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1->2->3->6->7
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(6);
        Node n5 = new Node(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        // 1->1->2->2->4->5->8
        Node m1 = new Node(1);
        Node m2 = new Node(1);
        Node m3 = new Node(2);
        Node m4 = new Node(2);
        Node m5 = new Node(4);
        Node m6 = new Node(5);
        Node m7 = new Node(8);
        m1.next = m2;
        m2.next = m3;
        m3.next = m4;
        m4.next = m5;
        m5.next = m6;
        m6.next = m7;

        Node merged = merge(n1, m1);
        printList(merged);
    }
}
