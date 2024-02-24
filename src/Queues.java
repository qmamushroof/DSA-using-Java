import java.util.*;
import java.util.LinkedList;

public class Queues {
    static class Queue_using_array {
        static int arr[];
        static int size;
        static int rear;

        Queue_using_array(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
        }

        public static boolean is_empty() {
            return rear == -1;
        }

        public static void add(int data) {
            if (rear == size - 1) {
                return;
            }
            rear++;
            arr[rear] = data;
        }

        public static int remove() {
            if (is_empty()) {
                return -1;
            }
            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear--;
            return front;
        }

        public static int peek() {
            if (is_empty()) {
                return -1;
            }
            return arr[0];
        }

        public static void print_and_remove() {
            while (!is_empty()) {
                System.out.print(peek() + " ");
                remove();
            }
            System.out.println();
        }
    }

    static class Circular_Queue_using_array {
        static int arr[];
        static int size;
        static int front;
        static int rear;

        Circular_Queue_using_array(int n) {
            arr = new int[n];
            size = n;
            front = -1;
            rear = -1;
        }

        public static boolean is_empty() {
            return rear == -1 && front == -1;
        }

        public static boolean is_full() {
            return (rear + 1) % size == front;
        }

        public static void add(int data) {
            if (is_full()) {
                return;
            }
            if (is_empty()) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        public static int remove() {
            if (is_empty()) {
                return -1;
            }
            int removed = arr[front];
            if (rear == front) {
                front = rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return removed;
        }

        public static int peek() {
            if (is_empty()) {
                return -1;
            }
            return arr[front];
        }

        public static void print_and_remove() {
            while (!is_empty()) {
                System.out.print(peek() + " ");
                remove();
            }
            System.out.println();
        }
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static class Queue_using_LinkedList {
        static Node head = null;
        static Node tail = null;

        public static boolean is_empty() {
            return tail == null && head == null;
        }

        public static void add(int data) {
            Node newNode = new Node(data);
            if (is_empty()) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        public static int remove() {
            if (is_empty()) {
                return -1;
            }
            int front = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return front;
        }

        public static int peek() {
            if (is_empty()) {
                return -1;
            }
            return head.data;
        }

        public static void print_and_remove() {
            while (!is_empty()) {
                System.out.print(peek() + " ");
                remove();
            }
            System.out.println();
        }
    }

    static class Queue_using_2_stacks {
        static Stack<Integer> stack1 = new Stack<>();
        static Stack<Integer> stack2 = new Stack<>();

        public static boolean is_empty() {
            return stack1.isEmpty();
        }

        public static void add(int data) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(data);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        public static int remove() {
            if (is_empty()) {
                return -1;
            }
            return stack1.pop();
        }

        public static int peek() {
            return stack1.peek();
        }

        public static void print_and_remove() {
            while (!is_empty()) {
                System.out.print(peek() + " ");
                remove();
            }
            System.out.println();
        }
    }

    static class Stack_using_2_queues {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public static boolean is_empty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public static void push(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        public static int pop() {
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else if (!q2.isEmpty()) {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }

        public static int peek() {
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else if (!q2.isEmpty()) {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }

        public static void print_and_pop() {
            while (!is_empty()) {
                System.out.print(peek() + " ");
                pop();
            }
            System.out.println();
        }
    }

    static class Queue_using_deque {
        static Deque<Integer> q = new LinkedList<>();

        public static void add(int data) {
            q.addLast(data);
        }

        public static int remove() {
            return q.removeFirst();
        }

        public static int peek() {
            return q.getFirst();
        }

        public static void print_and_remove() {
            while (!q.isEmpty()) {
                System.out.print(peek() + " ");
                remove();
            }
            System.out.println();
        }
    }

    static class Stack_using_deque {
        static Deque<Integer> q = new LinkedList<>();

        public static void push(int data) {
            q.addLast(data);
        }

        public static int pop() {
            return q.removeLast();
        }

        public static int peek() {
            return q.getLast();
        }

        public static void print_and_pop() {
            while (!q.isEmpty()) {
                System.out.print(peek() + " ");
                pop();
            }
            System.out.println();
        }
    }

    public static void reverse_queue(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();

        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    public static void interleave(Queue<Integer> q) {
        Queue<Integer> firstHalf = new LinkedList<>();
        int size = q.size();
        for (int i = 0; i < size / 2; i++) {
            firstHalf.add(q.remove());
        }
        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.remove());
            q.add(q.remove());
        }
    }

    public static void print_1st_nonRepeating_letters(String str) {
        Queue<Character> q = new LinkedList<>();
        int freq[] = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch - 'a']++;
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }
            if (q.isEmpty()) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(q.peek()+" ");
            }
        }

    }

    public static void main(String[] args) {
        // Queue<Integer> q = new LinkedList<>();
        // or ArrayDeque instead of LinkedList
        String str = "aabccxb";
        print_1st_nonRepeating_letters(str);
    }
}
