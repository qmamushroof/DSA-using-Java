import java.util.ArrayList;

public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public static void inorder_traversal(Node root) {
        if (root == null) {
            return;
        }
        inorder_traversal(root.left);
        System.out.print(root.data + " ");
        inorder_traversal(root.right);
    }

    public static void preorder_traversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder_traversal(root.left);
        preorder_traversal(root.right);
    }

    public static boolean search(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (root.data == value) {
            return true;
        }
        if (value < root.data) {
            return search(root.left, value);
        } else {
            return search(root.right, value);
        }
    }

    public static Node inorder_Successor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static Node delete(Node root, int nodeData) {
        if (nodeData < root.data) {
            root.left = delete(root.left, nodeData);
        } else if (nodeData > root.data) {
            root.right = delete(root.right, nodeData);
        } else {
            // leaf node
            if (root.left == null && root.right == null) {
                return null;
            }
            // single child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // double child
            Node inorderSuccessor = inorder_Successor(root);
            root.data = inorderSuccessor.data;
            root.right = delete(root.right, nodeData);
        }
        return root;
    }

    public static void print_path(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "->");
        }
        System.out.println("null");
    }

    public static void print_root_to_leaf(Node root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.left == null && root.right == null) {
            print_path(path);
        }
        print_root_to_leaf(root.left, path);
        print_root_to_leaf(root.right, path);
        path.remove(path.size() - 1);
    }

    // i wrote this code very differently yet it works
    public static void print_within_range(Node root, int lowerLimit, int upperLimit) {
        if (root == null) {
            return;
        }
        if (lowerLimit <= root.data && root.data <= upperLimit) {
            print_within_range(root.left, lowerLimit, upperLimit);
            System.out.print(root.data + " ");
            print_within_range(root.right, lowerLimit, upperLimit);
        } else if (root.data < lowerLimit) {
            print_within_range(root.right, lowerLimit, upperLimit);
        } else if (root.data > upperLimit) {
            print_within_range(root.left, lowerLimit, upperLimit);
        }
    }

    public static boolean is_valid_BST(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.data <= min.data) {
            return false;
        } else if (max != null && root.data >= max.data) {
            return false;
        }
        return is_valid_BST(root.left, min, root) && is_valid_BST(root.right, root, max);
    }

    public static Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node mirroredLeft = mirror(root.left);
        Node mirroredRight = mirror(root.right);

        root.left = mirroredRight;
        root.right = mirroredLeft;

        return root;
    }

    public static Node create_balanced_BST(ArrayList<Integer> arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(arr.get(mid));
        root.left = create_balanced_BST(arr, start, mid - 1);
        root.right = create_balanced_BST(arr, mid + 1, end);
        return root;
    }

    public static void get_inordered(Node root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        get_inordered(root.left, list);
        list.add(root.data);
        get_inordered(root.right, list);
    }

    public static Node balance_BST(Node root) { // O(n)
        ArrayList<Integer> list = new ArrayList<>();
        get_inordered(root, list);

        root = create_balanced_BST(list, 0, list.size() - 1);
        return root;
    }

    static class Info {
        boolean isBST;
        int size, min, max;

        Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxBST = 0;

    public static Info largest_BST_size(Node root) {
        if (root == null) {
            return new Info(true, 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info leftInfo = largest_BST_size(root.left);
        Info rightInfo = largest_BST_size(root.right);

        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, leftInfo.max));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        if (root.data <= leftInfo.max || root.data >= leftInfo.min) {
            return new Info(false, size, min, max);
        }
        if (leftInfo.isBST && rightInfo.isBST) {
            maxBST = Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }
        return new Info(false, size, min, max);
    }

    public static Node merge_BSTs(Node root1, Node root2) { // O(n+m)
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        get_inordered(root1, list1);
        get_inordered(root2, list2);

        ArrayList listFinal = new ArrayList<>();

        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                listFinal.add(list1.get(i));
                i++;
            } else {
                listFinal.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            listFinal.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            listFinal.add(list2.get(j));
            j++;
        }

        Node mergedBST = create_balanced_BST(listFinal, 0, listFinal.size() - 1);
        return mergedBST;
    }

    public static void main(String[] args) {
        // int values[] = { 3, 5, 6, 8, 10, 11, 12 };
        // int values[] = { 8, 6, 10, 5, 11, 3, 12 };
        // Node root = null;
        // for (int i = 0; i < values.length; i++) {
        // root = insert(root, values[i]);
        // }
        // Node root = new Node(50);
        // root.left = new Node(30);
        // root.left.left = new Node(5);
        // root.left.left.right = new Node(20);

        // root.right = new Node(60);
        // root.right.left = new Node(45);
        // root.right.right = new Node(70);
        // root.right.right.left = new Node(65);
        // root.right.right.right = new Node(80);

        // preorder_traversal(root);
        // root = balance_BST(root);
        // System.out.println();
        // preorder_traversal(root);

        // largest_BST_size(root);
        // System.out.println(maxBST);

        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);

        Node root2 = new Node(9);
        root2.left = new Node(3);
        root2.right = new Node(12);

        Node root = merge_BSTs(root1, root2);
        preorder_traversal(root);
    }
}
