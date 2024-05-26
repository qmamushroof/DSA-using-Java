public class SegmentTrees {
    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];
    }

    public static int build_tree(int arr[], int i, int start, int end) {// O(n)
        if (start == end) {
            tree[i] = arr[start];
            return arr[start];
        }

        int mid = (start + end) / 2;
        build_tree(arr, 2 * i + 1, start, mid);// left subtree
        build_tree(arr, 2 * i + 2, mid + 1, end);// rigth subtree
        tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
        return tree[i];

    }

    public static void print_tree() {
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public static int get_range_sum(int arr[], int queryStart, int queryEnd) { // O(logn)
        int n = arr.length;
        return get_range_sum_util(0, 0, n - 1, queryStart, queryEnd);
    }

    public static int get_range_sum_util(int i, int treeStart, int treeEnd, int queryStart, int queryEnd) { // O(logn)
        if (queryStart >= treeEnd || queryEnd <= treeStart) {// non-overlapping
            return 0;
        } else if (queryStart <= treeStart && treeEnd <= queryEnd) {// fully overlapping
            return tree[i];
        } else {// partially overlapping
            int mid = (treeStart + treeEnd) / 2;
            int left = get_range_sum_util(2 * i + 1, treeStart, mid, queryStart, queryEnd);
            int right = get_range_sum_util(2 * i + 2, mid + 1, treeEnd, queryStart, queryEnd);
            return left + right;
        }
    }

    public static void update_util(int i, int treeStart, int treeEnd, int idx, int difference) {// O(logn)
        if (idx < treeStart || idx > treeEnd) {// non-overlapping
            return;
        }
        tree[i] += difference;
        if (treeStart != treeEnd) {// non-leaf
            int mid = (treeStart + treeEnd) / 2;
            update_util(2 * i + 1, treeStart, mid, idx, difference);// left subtree
            update_util(2 * i + 2, mid + 1, treeEnd, idx, difference);// right subtree
        }
    }

    public static void update(int arr[], int idx, int newValue) {// O(logn)
        int n = arr.length;
        int difference = newValue - arr[idx];
        arr[idx] = newValue;
        update_util(0, 0, n - 1, idx, difference);
    }

    public static void build_max_tree(int arr[], int i, int start, int end) {// O(n)
        if (start == end) {// leaf node
            tree[i] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build_max_tree(arr, 2 * i + 1, start, mid);// left subtree
        build_max_tree(arr, 2 * i + 2, mid + 1, end);// right subtree
        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int get_max(int arr[], int start, int end) {// O(logn)
        int n = arr.length;
        return get_max_util(0, 0, n - 1, start, end);
    }

    public static int get_max_util(int i, int treeStart, int treeEnd, int queryStart, int queryEnd) {// O(logn)
        if (queryStart > treeEnd || queryEnd < treeStart) {// no overlap
            return Integer.MIN_VALUE;
        } else if (queryStart <= treeStart && treeEnd <= queryEnd) {// partial overlap
            return tree[i];
        } else {// full overlap
            int mid = (treeStart + treeEnd) / 2;
            int left = get_max_util(2 * i + 1, treeStart, mid, queryStart, queryEnd);
            int right = get_max_util(2 * i + 2, mid + 1, treeEnd, queryStart, queryEnd);
            return Math.max(left, right);
        }
    }

    public static void update_max(int arr[], int idx, int newValue) {// O(logn)
        int n = arr.length;
        arr[idx] = newValue;
        update_max_util(0, 0, n - 1, idx, newValue);
    }

    public static void update_max_util(int i, int treeStart, int treeEnd, int idx, int newValue) {// O(logn)
        if (idx < treeStart || idx > treeEnd) {
            return;
        }
        if (treeStart == treeEnd) {
            tree[i] = newValue;
        }
        if (treeStart != treeEnd) {
            tree[i] = Math.max(tree[i], newValue);
            int mid = (treeStart + treeEnd) / 2;
            update_max_util(2 * i + 1, treeStart, mid, idx, newValue);// left subtree
            update_max_util(2 * i + 2, mid + 1, treeEnd, idx, newValue);// right subtree
        }
    }

    public static void main(String[] args) {
        // int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };

        int n = arr.length;
        init(n);

        // build_tree(arr, 0, 0, n - 1);
        // print_tree();
        // System.out.println(get_range_sum(arr, 2, 5));
        // update(arr, 2, 2);
        // System.out.println(get_range_sum(arr, 2, 5));

        // build_max_tree(arr, 0, 0, n - 1);
        // System.out.println(get_max(arr, 2, 5));
        // update_max(arr, 2, 20);
        // System.out.println(get_max(arr, 2, 5));
    }
}