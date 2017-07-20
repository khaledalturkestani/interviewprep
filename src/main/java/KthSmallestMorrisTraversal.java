import org.omg.PortableInterceptor.INACTIVE;

/**
 * Morris Traversal: Inorder traversal of BST with O(1) extra space (no recursion)
 */
public class KthSmallestMorrisTraversal {

    public static int inorderKthElement(Node root, int k) {
        if (root == null || k <= 0)
            return Integer.MAX_VALUE;

        int count = 0;
        int kthSmallest = Integer.MAX_VALUE;
        Node pre = null;    // Inorder predecessor
        Node cur = root;
        while (cur != null) {
            if (cur.left == null) {
                count++;
                if (count == k)
                    kthSmallest = cur.value;
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right.value != cur.value) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;    // Alter BST
                    cur = cur.left;
                } else {
                    pre.right = null;   // Reset BST
                    count++;
                    if (count == k)
                        kthSmallest = cur.value;
                    cur = cur.right;
                }
            }
        }
        return kthSmallest;
    }

    public static void main(String args[]) {
        //            20
        //          /   \
        //         8     22
        //        /\
        //       4  12
        //          / \
        //         10  14
        Node root             = new Node(20);
        root.left             = new Node(8);
        root.right            = new Node(22);
        root.left.left        = new Node(4);
        root.left.right       = new Node(12);
        root.left.right.left  = new Node(10);
        root.left.right.right = new Node(14);

        int kth = inorderKthElement(root, 1);
        System.out.println("Expected smallest element: " + 4 + ". Got: " + kth);

        kth = inorderKthElement(root, 2);
        System.out.println("Expected 2nd smallest element: " + 8 + ". Got: " + kth);

        kth = inorderKthElement(root, 3);
        System.out.println("Expected 2nd smallest element: " + 10 + ". Got: " + kth);

        kth = inorderKthElement(root, 4);
        System.out.println("Expected 2nd smallest element: " + 12 + ". Got: " + kth);

        kth = inorderKthElement(root, 5);
        System.out.println("Expected 2nd smallest element: " + 14 + ". Got: " + kth);
    }
}
