public class BST {

    public static class Node {
        private int value;
        private int height;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.height = 0;
        }
        public int getValue() {
            return this.value;
        }
    }
    public Node root;
    public BST() {

    }
    public int height(Node node) {
        if(node == null)
            return -1;
        return node.height;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public void prettyPrint() {
        display(root, 0);
    }
    private void display(Node node, int level) {
        if(node == null)
            return;
        display(node.right, level+1);
        if(level != 0) {
            for(int i=0 ; i<level-1 ; ++i)
                System.out.print("|\t\t");
            System.out.println("|------>" + node.value);
        } else {
            System.out.println(node.value);
        }
        display(node.left, level+1);

    }
    public void insert(int value) {
        root = insert(value, this.root);
    }
    private Node insert(int value, Node node) {
        if(node == null) {
            node = new Node(value);
            return node;
        }
        if(value < node.value)
            node.left = insert(value, node.left);
        if(value > node.value)
            node.right = insert(value, node.right);

        node.height = Math.max( height(node.left), height(node.right) ) + 1;
        return node;
    }

    public void populate(int[] nums) {
        for(int i : nums)
            this.insert(i);
    }

    public void populateSorted(int[] nums, int start, int end) {
        if(start >= end)
            return;
        int mid = (start + end)/2;
        this.insert(nums[mid]);
        populateSorted(nums, start, mid);
        populateSorted(nums, mid+1, end);
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if(node == null)
            return true;
        return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    public static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public Node buildTree(int[] pre, int[] in) {
        if(pre.length == 0)
            return null;
        int r = pre[0];
        int index = find(in, r);

        Node node = new Node(r);
        node.left = buildTree( copyOfRange(pre, 1, index+1), copyOfRange(in, 0, index));
        node.right = buildTree( copyOfRange(pre, index+1, pre.length), copyOfRange(in, index+1, in.length));
        return node;
    }
    public static int[] copyOfRange(int[] arr, int start, int end) {
        int[] subArr = new int[end-start];
        for(int i=start ; i<end ; ++i)
            subArr[i-start] = arr[i];
        return subArr;
    }
    public static int find(int[] arr, int elem) {
        for(int i=0 ; i<arr.length ; ++i)
            if(arr[i] == elem)
                return i;
        return arr.length;
    }
     public static void main(String[] args) {
        BST binTree = new BST();
        int[] in = {0, 1, 2, 3, 4, 5, 6};
        int[] pre = {3, 1, 0, 2, 5, 4, 6};
        binTree.root = binTree.buildTree(pre, in);
        binTree.prettyPrint();

    }
}
