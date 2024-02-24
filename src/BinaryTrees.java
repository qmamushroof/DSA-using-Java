import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BinaryTrees {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int i = -1;

        public static Node build_tree(int nodes[]) {
            i++;
            if (nodes[i] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[i]);
            newNode.left = build_tree(nodes);
            newNode.right = build_tree(nodes);
            return newNode;
        }

        public static void preorder_traversal(Node root) {
            if (root == null) {
                // System.out.print(-1 + " ");
                return;
            }
            System.out.print(root.data + " ");
            preorder_traversal(root.left);
            preorder_traversal(root.right);
        }

        public static void inorder_traversal(Node root) {
            if (root == null) {
                // System.out.print(-1 + " ");
                return;
            }
            inorder_traversal(root.left);
            System.out.print(root.data + " ");
            inorder_traversal(root.right);
        }

        public static void postorder_traversal(Node root) {
            if (root == null) {
                // System.out.print(-1 + " ");
                return;
            }
            postorder_traversal(root.left);
            postorder_traversal(root.right);
            System.out.print(root.data + " ");
        }

        public static void levelorder_traversal(Node root) { // BFS
            if (root == null) {
                // System.out.print(-1 + " ");
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }

        }
    }

    public static int height(Node root) { // in terms of nodes
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int count(Node root) {
        if (root == null) {
            return 0;
        }
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        return leftCount + rightCount + 1;
    }

    public static int sum(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return leftSum + rightSum + root.data;
    }

    public static int diameter_polynomial(Node root) { // O(n^2)
        if (root == null) {
            return 0;
        }
        int leftDiameter = diameter_polynomial(root.left);
        int rightDiameter = diameter_polynomial(root.right);
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int selfDiameter = leftHeight + rightHeight + 1;
        return Math.max(Math.max(rightDiameter, leftDiameter), selfDiameter);
    }

    public static class Info_diameter {
        int diameter;
        int height;

        public Info_diameter(int d, int h) {
            diameter = d;
            height = h;
        }
    }

    public static Info_diameter diameter(Node root) { // O(n)
        if (root == null) {
            return new Info_diameter(0, 0);
        }
        Info_diameter leftInfo = diameter(root.left);
        Info_diameter rightInfo = diameter(root.right);

        int diameter = Math.max(leftInfo.height + rightInfo.height + 1,
                Math.max(leftInfo.diameter, rightInfo.diameter));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info_diameter(diameter, height);
    }

    public static boolean isIdentical(Node node, Node subroot) {
        if (node == null && subroot == null) {
            return true;
        } else if (node == null || subroot == null || node.data != subroot.data) {
            return false;
        }

        if (!isIdentical(node.left, subroot.left)) {
            return false;
        }
        if (!isIdentical(node.right, subroot.right)) {
            return false;
        }
        return true;
    }

    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null) {
            return false;
        }
        if (root.data == subroot.data) {
            if (isIdentical(root, subroot)) {
                return true;
            }
        }
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    public static class Info_topview {
        Node node;
        int horizontalDistance;

        public Info_topview(Node n, int h) {
            node = n;
            horizontalDistance = h;
        }
    }

    public static ArrayList<Integer> topview(Node root) {
        Queue<Info_topview> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();

        int min = 0, max = 0;
        q.add(new Info_topview(root, 0));
        q.add(null);
        while (!q.isEmpty()) {
            Info_topview curr = q.remove();
            if (curr == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                if (!map.containsKey(curr.horizontalDistance)) {
                    map.put(curr.horizontalDistance, curr.node);
                }
                if (curr.node.left != null) {
                    q.add(new Info_topview(curr.node.left, curr.horizontalDistance - 1));
                    min = Math.min(curr.horizontalDistance - 1, min);
                }
                if (curr.node.right != null) {
                    q.add(new Info_topview(curr.node.right, curr.horizontalDistance + 1));
                    max = Math.max(curr.horizontalDistance + 1, min);
                }
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            arr.add(map.get(i).data);
        }
        return arr;
    }

    public static void kth_level(Node node, int level, int k) {// O(1) //recursive approach
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return;
        }
        if (level == k) {
            list.add(node.data);
            System.out.print(node.data + " ");
            return;
        }
        kth_level(node.left, level + 1, k);
        kth_level(node.right, level + 1, k);
        return;
    }

    public static boolean get_path(Node root, int seacrhedData, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.data == seacrhedData) {
            return true;
        }
        boolean foundLeft = get_path(root.left, seacrhedData, path);
        boolean foundRight = get_path(root.right, seacrhedData, path);
        if (foundLeft || foundRight) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static Node nearest_common_ancestor(Node root, int nodeData1, int nodeData2) { // aka last common ancestor
                                                                                          // O(n)
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        get_path(root, nodeData1, path1);
        get_path(root, nodeData2, path2);

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        return path1.get(i - 1);
    }

    public static Node nearest_common_ancestor_2(Node root, int nodeData1, int nodeData2) { // aka last common ancestor
                                                                                            // O(n)
        if (root == null || root.data == nodeData1 || root.data == nodeData2) {
            return root;
        }
        Node leftResult = nearest_common_ancestor_2(root.left, nodeData1, nodeData2);
        Node rightResult = nearest_common_ancestor_2(root.right, nodeData1, nodeData2);
        if (leftResult == null) {
            return rightResult;
        }
        if (rightResult == null) {
            return leftResult;
        }
        return root;
    }

    public static int distance(Node root, int nodeData) {
        if (root == null) {
            return -1;
        }
        if (root.data == nodeData) {
            return 0;
        }
        int leftDistance = distance(root.left, nodeData);
        int rightDistance = distance(root.right, nodeData);
        if (leftDistance == -1 && rightDistance == -1) {
            return -1;
        } else if (leftDistance == -1) {
            return rightDistance + 1;
        } else {
            return leftDistance + 1;
        }
    }

    public static int minimum_distance(Node root, int nodeData1, int nodeData2) { // O(n)
        Node lca = nearest_common_ancestor(root, nodeData1, nodeData2);
        int distance1 = distance(lca, nodeData1);
        int distance2 = distance(lca, nodeData2);
        return distance1 + distance2;
    }

    public static int kth_ancestor(Node root, int nodeData, int k) {
        if (root == null) {
            return -1;
        }
        if (root.data == nodeData) {
            return 0;
        }
        int leftDistance = kth_ancestor(root.left, nodeData, k);
        int rightDistance = kth_ancestor(root.right, nodeData, k);

        if (leftDistance == -1 && rightDistance == -1) {
            return -1;
        }
        int max = Math.max(leftDistance, rightDistance);
        if (max + 1 == k) {
            System.out.println(root.data);
        }
        return max + 1;
    }

    public static int transform_to_sum_tree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = transform_to_sum_tree(root.left);
        int rightSum = transform_to_sum_tree(root.right);

        int newLeft = root.left == null ? 0 : root.left.data;
        int newRight = root.right == null ? 0 : root.right.data;

        int data = root.data;
        root.data = newLeft + leftSum + newRight + rightSum;
        return data;
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTrees bt = new BinaryTrees();
        // Node root = bt.build_tree(nodes);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Node subroot = new Node(2);
        // subroot.left = new Node(4);
        // subroot.right = new Node(5);
        transform_to_sum_tree(root);
        // bt.preorder_traversal(root);

    }
}
