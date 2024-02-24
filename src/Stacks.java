import java.util.*;

public class Stacks {
    static class Stack_using_arraylist {
        static ArrayList<Integer> list = new ArrayList<>();

        public static boolean is_empty() {
            return list.size() == 0;
        }

        public static void push(int data) {
            list.add(data);
        }

        public static int pop() {
            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        public static int peek() {
            if (is_empty()) {
                return -1;
            }
            return list.get(list.size() - 1);
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

    static class Stack_using_LinkedList {
        static Node head = null;

        public static boolean is_empty() {
            return head == null;
        }

        public static void push(int data) {
            Node newNode = new Node(data);
            if (is_empty()) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }

        public static int pop() {
            if (is_empty()) {
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }

        public static int peek() {
            if (is_empty()) {
                return -1;
            }
            int top = head.data;
            return top;
        }

    }

    public static void push_bottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        push_bottom(s, data);
        s.push(top);
    }

    public static String reverse_string(String str) {
        Stack<Character> s = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            s.push(str.charAt(i));
            i++;
        }
        StringBuilder sb = new StringBuilder("");
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    public static void print_stack(Stack s) {
        while (!s.isEmpty()) {
            System.out.print("[" + s.pop());
        }
    }

    public static void reverse(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int top = s.pop();
        reverse(s);
        push_bottom(s, top);
    }

    public static void stock_span(int stock[], int span[]) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);
        for (int i = 1; i < span.length; i++) {
            int currPrice = stock[i];
            while (!s.isEmpty() && currPrice > stock[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHighIdx = s.peek();
                span[i] = i - prevHighIdx;
            }
            s.push(i);
        }
    }

    public static int[] next_greater_element(int arr[]) {
        Stack<Integer> s = new Stack<>();
        int nextGreaterIdx[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nextGreaterIdx[i] = -1;
            } else {
                nextGreaterIdx[i] = arr[s.peek()];
            }
            s.push(i);
        }
        return nextGreaterIdx;
    }

    public static boolean is_valid(String str) {
        Stack<Character> s = new Stack<>();
        int i;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                s.push(str.charAt(i));
            } else if (s.isEmpty()) {
                return false;
            } else if ((s.peek() == '(' && str.charAt(i) == ')')
                    || (s.peek() == '{' && str.charAt(i) == '}')
                    || (s.peek() == '[' && str.charAt(i) == ']')) {
                s.pop();
            }
        }
        if (s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean is_duplicate(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                int count = 0;
                while (s.pop() != '(') {
                    count++;
                }
                if (count < 1) {
                    return true;
                }
            } else {
                s.push(ch);
            }
        }
        return false;
    }

    public static int max_area(int height[]) {
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        int left_small[] = new int[height.length];
        int right_small[] = new int[height.length];
        for (int i = height.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && height[s.peek()] >= height[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                right_small[i] = height.length;
            } else {
                right_small[i] = s.peek();
            }
            s.push(i);
        }

        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[s.peek()] >= height[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                left_small[i] = -1;
            } else {
                left_small[i] = s.peek();
            }
            s.push(i);
        }

        for (int i = 0; i < height.length; i++) {
            int currArea = (right_small[i] - left_small[i] - 1) * height[i];
            maxArea = Math.max(maxArea, currArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Stack_using_arraylist s = new Stack_using_arraylist();
        // Stack_using_LinkedList s = new Stack_using_LinkedList();

        // Stack<Integer> s = new Stack<>();
        // s.push(0);
        // s.push(1);
        // s.push(2);
        // s.push(3);
        // reverse(s);
        // print_stack(s);

        // int stocks[] = { 100, 80, 60, 70, 60, 85, 100 };
        // int span[] = new int[stocks.length];
        // stock_span(stocks, span);
        // for (int i = 0; i < span.length; i++) {
        // System.out.print(span[i] + " ");
        // }

        // int arr[] = { 6, 8, 0, 1, 3 };
        // int next[] = next_greater_element(arr);

        // for (int i = 0; i < next.length; i++) {
        // System.out.print(next[i] + " ");
        // }

        // String str="(){[]}(";
        // System.out.println(is_valid(str));

        // String str = "(a+b+((c-d)))";
        // System.out.println(is_duplicate(str));

        int arr[] = { 2, 1, 5, 6, 2, 3 };
        System.out.println(max_area(arr));
    }
}