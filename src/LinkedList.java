// import java.util.LinkedList;

public class LinkedList {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head, tail;
    public static int size = 0;

    public void add_first(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void add(int data, int index) {
        Node newNode = new Node(data);
        if (index == 0) {
            add_first(data);
            return;
        }
        size++;
        Node finder = head;
        int i = 0;
        while (i < index - 1) {
            finder = finder.next;
            i++;
        }
        newNode.next = finder.next;
        finder.next = newNode;

    }

    public void add_last(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void print() {
        Node finder = head;
        while (finder != null) {
            System.out.print(finder.data + "->");
            finder = finder.next;
        }
        System.out.println("null");
    }

    public int remove_last() {
        if (size == 0) {
            System.out.println("Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int data = head.data;
            head = tail = null;
            size = 0;
            return data;
        }
        int data = tail.data;
        Node finder = head;
        for (int i = 0; i < size - 2; i++) {
            finder = finder.next;
        }
        finder.next = null;
        tail = finder;
        size--;
        return data;
    }

    public int remove_1st() {
        if (size == 0) {
            System.out.println("Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int data = head.data;
            head = tail = null;
            size = 0;
            return data;
        }
        int data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public void remove(int index) {
        if (index == 0) {
            remove_1st();
            return;
        } else if (index == size - 1) {
            remove_last();
            return;
        }
        Node finder = head;
        int i = 0;
        while (i < index - 1) {
            finder = finder.next;
            i++;
        }
        finder.next = finder.next.next;
    }

    // iterative
    public int search(int key) {
        int i = 0;
        for (Node finder = head; finder.next != null; i++, finder = finder.next) {
            if (finder.data == key) {
                return i;
            }
        }
        return -1;
    }

    public int search(Node head, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int index = search(head.next, key);
        if (index == -1) {
            return -1;
        }
        return index + 1;
    }

    public void reverse() {
        Node prev = null, next;
        Node curr = tail = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public Node find_mid(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean check_palindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        Node curr = find_mid(head);
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left_head = head;
        Node right_head = prev;
        while (right_head != null) {
            if (left_head.data != right_head.data) {
                return false;
            }
            left_head = left_head.next;
            right_head = right_head.next;
        }

        return true;
    }

    public boolean has_cycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public void remove_cycle() {
        Node slow = head;
        Node fast = head;
        Boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return;
        }
        slow = head;
        Node prev = null;
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = null;
    }

    public Node get_mid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node merge(Node leftHead, Node rightHead) {
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;

        while (leftHead != null && rightHead != null) {
            if (leftHead.data <= rightHead.data) {
                temp.next = leftHead;
                leftHead = leftHead.next;
                temp = temp.next;
            } else {
                temp.next = rightHead;
                rightHead = rightHead.next;
                temp = temp.next;
            }
        }

        while (leftHead != null) {
            temp.next = leftHead;
            leftHead = leftHead.next;
            temp = temp.next;
        }
        while (rightHead != null) {
            temp.next = rightHead;
            rightHead = rightHead.next;
            temp = temp.next;
        }
        return mergedLL.next;
    }

    public Node merge_sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node mid = get_mid(head);
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = merge_sort(head);
        Node newRight = merge_sort(rightHead);

        return merge(newLeft, newRight);
    }

    public void zigzag() {
        Node mid = get_mid(head);
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = head;
        Node right = prev;
        Node nextOfLeft, nextOfRight;
        while (left != null & right != null) {
            nextOfLeft = left.next;
            left.next = right;
            nextOfRight = right.next;
            right.next = nextOfLeft;

            left = nextOfLeft;
            right = nextOfRight;
        }
    }

    public static void main(String[] args) {
        // LinkedList<Integer> ll= new LinkedList<>();

        LinkedList l1 = new LinkedList();
        l1.add_last(1);
        l1.add_last(2);
        l1.add_last(3);
        l1.add_last(4);
        l1.add_last(5);
        l1.print();
        l1.zigzag();
        l1.print();
    }
}