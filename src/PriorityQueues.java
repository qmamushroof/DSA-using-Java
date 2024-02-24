
// import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueues {

    static class Student implements Comparable<Student> {
        String name;
        int rank;

        Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        // @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }

    static class Point implements Comparable<Point> {
        int x, y, index, distanceSq;

        Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            distanceSq = x * x + y * y;
        }

        public int compareTo(Point p2) {
            return this.distanceSq - p2.distanceSq;
        }
    }

    public static void k_nearest_points(int points[][], int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < points.length; i++) {
            pq.add(new Point(points[i][0], points[i][1], i));
        }
        for (int i = 0; i < k; i++) {
            System.out.print(pq.remove().index + " ");
        }
    }

    public static int min_cost_to_connect_n_ropes(int[] ropes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < ropes.length; i++) {
            pq.add(ropes[i]);
        }
        int cost = 0;
        while (pq.size() > 1) {
            int min1 = pq.remove();
            int min2 = pq.remove();
            int newLength = min1 + min2;
            pq.add(newLength);
            cost += newLength;
        }

        return cost;
    }

    static class Army implements Comparable<Army> {
        int soldiers, index;

        Army(int soldiers, int index) {
            this.soldiers = soldiers;
            this.index = index;
        }

        @Override
        public int compareTo(Army row) {
            if (this.soldiers == row.soldiers) {
                return this.index - row.index;
            } else {
                return this.soldiers - row.soldiers;
            }
        }
    }

    public static void weakest_soldiers(int army[][]) {
        PriorityQueue<Army> pq = new PriorityQueue<>();
        for (int i = 0; i < army.length; i++) {
            int count = 0;
            for (int j = 0; j < army[0].length; j++) {
                count += army[i][j];
            }
            pq.add(new Army(count, i));
        }

        while (!pq.isEmpty()) {
            System.out.print(pq.remove().index + " ");
        }
    }

    static class Number implements Comparable<Number> {
        int value, index;

        Number(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int compareTo(Number n) {
            return n.value - this.value; // descending
        }
    }

    // Deque is more efficient
    public static int[] silding_window_max(int arr[], int windowSize) { // O(nlogk)
        PriorityQueue<Number> pq = new PriorityQueue<>();
        int result[] = new int[arr.length - windowSize + 1];

        for (int i = 0; i < windowSize; i++) {
            pq.add(new Number(arr[i], i));
        }
        result[0] = pq.peek().value;
        for (int i = windowSize; i < arr.length; i++) {
            while (!pq.isEmpty() && pq.peek().index <= i - windowSize) {
                pq.remove();
            }
            pq.add(new Number(arr[i], i));
            result[i - windowSize + 1] = pq.peek().value;
        }
        return result;
    }

    public static void main(String[] args) {
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // PriorityQueue<Student> stu = new PriorityQueue<>();

        // stu.add(new Student("A", 4));
        // stu.add(new Student("B", 5));
        // stu.add(new Student("C", 2));
        // stu.add(new Student("D", 12));
        // stu.add(new Student("E", 4));

        // while (!stu.isEmpty()) {
        // System.out.println(stu.peek().name + " " + stu.peek().rank);
        // stu.remove();
        // }

        // int points[][] = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        // int k = 2;
        // k_nearest_points(points, k);

        // int ropes[] = { 2, 3, 3, 4, 6 };
        // System.out.println(min_cost_to_connect_n_ropes(ropes));

        // int army[][] = {
        // { 1, 0, 0, 0 },
        // { 1, 1, 1, 1 },
        // { 1, 0, 0, 0 },
        // { 1, 0, 0, 0 }
        // };
        // weakest_soldiers(army);

        int arr[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int res[] = silding_window_max(arr, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
