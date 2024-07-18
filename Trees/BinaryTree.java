import java.util.Scanner;

public class BinaryTree {
    public BinaryTree() {}

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    private Node root;

    public void populate(Scanner scan) {
         System.out.print("Enter the root node : ");
         int value = scan.nextInt();
         root = new Node(value);
         populate(scan, root);
    }

    private void populate(Scanner scan, Node node) {

        System.out.print("Do you want to enter left of " + node.value + " ? ");
        boolean hasLeft = scan.nextBoolean();
        if( hasLeft ) {
            System.out.print("Enter the left node : ");
            int leftVal = scan.nextInt();
            node.left = new Node(leftVal);
            populate(scan, node.left);
        }

        System.out.print("Do you want to enter right of " + node.value + " ? ");
        boolean hasRight = scan.nextBoolean();
        if( hasRight ) {
            System.out.print("Enter the right node : ");
            int rightVal = scan.nextInt();
            node.right = new Node(rightVal);
            populate(scan, node.right);
        }
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

    public static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public static void preOrder(Node root) {
        if(root == null)
            return;
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(Node root) {
        if(root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.populate(new Scanner(System.in));
        tree.prettyPrint();

    }

}
