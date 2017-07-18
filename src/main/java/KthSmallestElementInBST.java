public class KthSmallestElementInBST {

    public static class MutableInteger {
        private int value;
        MutableInteger(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public void incr() {
            value++;
        }
    }
    private static Node kthSmallest(Node root, int k) {
        MutableInteger c = new MutableInteger(0);
        return kthSmallestUtil(root, k, c);
    }

    private static Node kthSmallestUtil(Node root, int k, MutableInteger c) {
        if (root == null || c.getValue() >= k)
            return null;

        Node leftRec = kthSmallestUtil(root.left, k, c);

        c.incr();

        if (c.getValue() == k)
            return root;

        Node rightRec = kthSmallestUtil(root.right, k, c);

        if (rightRec != null)
            return rightRec;

        return leftRec;
    }

    public static void main(String[] args) {
        //            20
        //          /   \
        //         8     22
        //        /\
        //       4  12
        //          / \
        //         10  14
        Node root             = new Node(20);
        Node eight            = new Node(8);
        root.left             = eight;
        Node twentyTwo        = new Node(22);
        root.right            = twentyTwo;
        Node four             = new Node(4);
        root.left.left        = four;
        Node twelve           = new Node(12);
        root.left.right       = twelve;
        Node ten              = new Node(10);
        root.left.right.left  = ten;
        Node fourtneen        = new Node(14);
        root.left.right.right = fourtneen;

        Node answer = kthSmallest(root, 1);
        System.out.println(String.format("Expected 1st smallest value is %d. Got: %d", 4, answer.value));

        answer = kthSmallest(root, 2);
        System.out.println(String.format("Expected 2nd smallest value is %d. Got: %d", 8, answer.value));

        answer = kthSmallest(root, 3);
        System.out.println(String.format("Expected 3rd smallest value is %d. Got: %d", 10, answer.value));

        answer = kthSmallest(root, 4);
        System.out.println(String.format("Expected 4th smallest value is %d. Got: %d", 12, answer.value));

    }
}
