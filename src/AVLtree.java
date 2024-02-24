public class AVLtree {
    /*
     * in interviews, it is asked to explain AVL trees and how rotation is performed
     * instead of writing code
     */
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    public static int balance_factor(Node root) {
        if (root == null) {
            return 0;
        }
        return height(root.left) - height(root.right);
    }

    public static Node left_rotate(Node node1) {
        Node node2 = node1.right;
        Node node3 = node2.left;

        // rotation
        node2.left = node1;
        node1.right = node3;

        node1.height = 1 + Math.max(height(node1.left), height(node1.right));
        node2.height = 1 + Math.max(height(node2.left), height(node2.right));

        return node2;
    }

    public static Node right_rotate(Node node1) {
        Node node2 = node1.left;
        Node node3 = node2.right;

        // rotation
        node2.right = node1;
        node1.left = node3;

        // update height
        node1.height = 1 + Math.max(height(node1.left), height(node1.right));
        node2.height = 1 + Math.max(height(node2.left), height(node2.right));

        return node2;
    }

    public static Node insert(Node root, int input) {
        if (root == null) {
            return new Node(input);
        }
        if (input < root.data) {
            root.left = insert(root.left, input);
        } else if (input > root.data) {
            root.right = insert(root.right, input);
        } else {
            return root;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balanceFactor = balance_factor(root);

        // left left
        if (balanceFactor > 1 && input < root.left.data) {
            return right_rotate(root);
        }
        // left right
        if (balanceFactor > 1 && input > root.left.data) {
            root.left = left_rotate(root.left);
            return right_rotate(root);
        }
        // right left
        if (balanceFactor < -1 && input < root.right.data) {
            root.right = right_rotate(root.right);
            return left_rotate(root);
        }
        // right right
        if (balanceFactor < -1 && input > root.right.data) {
            return left_rotate(root);
        }
        return root;
    }

    public static void preorder_traversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder_traversal(root.left);
        preorder_traversal(root.right);
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 10);
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        root = insert(root, 25);
        preorder_traversal(root);
    }
}
