import java.util.ArrayList;

public class Heaps {
    static class Heap { // minheap
        ArrayList<Integer> arr = new ArrayList<>();

        void add(int data) { // O(log(n))
            arr.add(data);

            int childIdx = arr.size() - 1;
            int parentIdx = (childIdx - 1) / 2;

            while (arr.get(parentIdx) > arr.get(childIdx)) { // O(log(n))
                int temp = arr.get(parentIdx);
                arr.set(parentIdx, arr.get(childIdx));
                arr.set(childIdx, temp);

                // update pointer
                childIdx = parentIdx;
                parentIdx = (childIdx - 1) / 2;
            }
        }

        public int peek() {
            return arr.get(0);
        }

        private void heapify(int index) { // O(log(n))
            int leftIdx = 2 * index + 1;
            int rightIdx = 2 * index + 2;
            int minIdx = index;

            if (leftIdx < arr.size() && arr.get(minIdx) > arr.get(leftIdx)) {
                minIdx = leftIdx;
            }
            if (rightIdx < arr.size() && arr.get(minIdx) > arr.get(rightIdx)) {
                minIdx = rightIdx;
            }
            if (minIdx != index) {
                int temp = arr.get(index);
                arr.set(index, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx);
            }
        }

        public int remove() { // O(log(n))
            int data = arr.get(0);

            // swap 1st & last
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, data);

            // remove last
            arr.remove(arr.size() - 1);

            // heapify
            heapify(0); // O(log(n))

            return data;
        }

        public boolean is_empty() {
            return arr.size() == 0;
        }
    }

    public static void heapify_max(int arr[], int index, int size) {
        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;
        int maxIdx = index;

        if (leftIdx < size && arr[index] < arr[leftIdx]) {
            maxIdx = leftIdx;
        }

        if (rightIdx < size && arr[index] < arr[rightIdx]) {
            maxIdx = rightIdx;
        }

        if (maxIdx != index) {
            int temp = arr[index];
            arr[index] = arr[maxIdx];
            arr[maxIdx] = temp;

            heapify_max(arr, maxIdx, size);
        }
    }

    public static void heap_sort(int arr[]) { // O(nlogn)
        int n = arr.length;
        // 1.build max-heap
        for (int i = n / 2; i >= 0; i--) {
            heapify_max(arr, i, n);
        }
        // 2.push largest to end
        for (int i = n - 1; i >= 0; i--) {
            // 2.1.swap largest with the last
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 2.2.
            heapify_max(arr, 0, i);
        }
    }

    static void remove_and_print(Heap h) {
        while (!h.is_empty()) {
            System.out.print(h.remove() + " ");
        }
    }

    public static void main(String[] args) {
        // Heap h = new Heap();
        // h.add(3);
        // h.add(4);
        // h.add(1);
        // h.add(5);

        // remove_and_print(h);

        // int arr[] = { 5, 4, 3, 2, 1 };
        // heap_sort(arr);
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i]);
        // }
    }
}
