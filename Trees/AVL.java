import java.util.NoSuchElementException;

public class AVL {
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
    private int nodeCount;
    public AVL() {
        nodeCount=0;
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
        nodeCount++;
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

        return avl(node);
    }

    private Node avl(Node node) {
        // condition for left-subtree unbalance
        if( height(node.left) - height(node.right) > 1){
            // case 1 : left-left unbalance
            if( height(node.left.left) > height(node.left.right))
                return rightRotate(node);
            // case 2 : left-right unbalance
            if( height(node.left.left) < height(node.left.right)) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        // condition for right-subtree unbalbance
        if( height(node.left) - height(node.right) < -1){
            // case 2 : right-right unbalance
            if( height(node.right.left) < height(node.right.right))
                return leftRotate(node);
            // case 2 : left-right unbalance
            if( height(node.right.left) > height(node.right.right)) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private Node leftRotate(Node node) {
        Node c = node;
        Node p = node.right;
        Node t = p.left;

        c.right = t;
        p.left = c;

        c.height = Math.max( height(c.left), height(c.right)) + 1;
        p.height = Math.max( height(p.left), height(p.right)) + 1;
        return p;
    }

    private Node rightRotate(Node node) {
        Node p = node;
        Node c = node.left;
        Node t = c.right;

        p.left = t;
        c.right = p;

        c.height = Math.max( height(c.left), height(c.right)) + 1;
        p.height = Math.max( height(p.left), height(p.right)) + 1;
        return c;
    }

    public void populate(int[] nums) {
        for(int i : nums)
            this.insert(i);
        nodeCount += nums.length;
    }


    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if(node == null)
            return true;
        return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    public int remove(int key) throws NoSuchElementException{
        if( !find(root, key) )
            throw new NoSuchElementException();

        return remove(root, key).value;
    }
    private Node remove(Node node, int key) {
        if(node == null)
            return node;
        else if(key < node.value)
            node.left = remove(node.left, key);
        else if(node.value < key)
            node.right =  remove(node.right, key);
        else {
            if(node.left == null) {
                return node.right;
            } else if( node.right == null) {
                return node.left;
            } else {
                Node rep = inOrderSuccessor(node.right);
                node.value = rep.value;
                node.right = remove(node.right, node.value);
            }
        }
        return avl(node);
    }
    public Node inOrderSuccessor(Node node) {
        while(node.left != null)
            node = node.left;
        return node;
    }
    public static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }
    public static void postOrder(Node root) {
        if(root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }

    public boolean search(int val) {
        return find(root, val);
    }

    private boolean find(Node node, int key) {
        if(node == null)
            return false;
        else if(node.value == key)
            return true;
        else if(key < node.value)
            return find(node.left, key);
        else // if(key > node.value)
            return find(node.right, key);
    }

    public int nonLeafCount() {
        return nodeCount/2;
    }
    public int leafCount() {
        return (nodeCount+ 1)/2;
    }

    public static void main(String[] args) {
        AVL tree = new AVL();

        int[] arr = {};

        tree.populate(arr);
//        tree.prettyPrint();
        System.out.println( tree.height(tree.root) + 1);

    }


}

