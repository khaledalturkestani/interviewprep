/**
 * Created by khaledalturkestani on 12/9/17.
 */
public class IsPalindrome {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    public boolean isPalindrome(ListNode head) {
        int size = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            size++;
        }
        if (size <= 1)
            return true;
        ListNode prev = null;
        ListNode cur = head;
        for (int i = 0; i < size/2; i++) {
            prev = cur;
            cur = cur.next;
        }
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur = prev;

        ListNode left = head;
        ListNode right = cur;
        boolean isPalindrome = true;
        for (int i = 0; i < size/2; i++) {
            if (left.val != right.val)
                isPalindrome = false;
            left = left.next;
            right = right.next;
        }
        // Re-reverse
        prev = null;
        for (int i = 0; i <= size/2; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return isPalindrome;
    }

    public static void main(String[] args) {
//        ListNode head1 = new ListNode(1);
//        ListNode head2 = new ListNode(3);
//        ListNode head3 = new ListNode(0);
//        ListNode head4 = new ListNode(2);
//        head1.next = head2;
//        head2.next = head3;
//        head3.next = head4;
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head1.next = head2;
        System.out.println(new IsPalindrome().isPalindrome(head1));
    }
}
