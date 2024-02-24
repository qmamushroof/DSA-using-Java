public class DoubleyLinkedList {
    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    public static Node head, tail;
    public static int size = 0;

    public void add_first(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void remove_first() {
        if (head == null) {
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
    }

    public void print() {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "<-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void reverse() {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public static void main(String[] args) {
        DoubleyLinkedList dll = new DoubleyLinkedList();
        dll.add_first(1);
        dll.add_first(2);
        dll.add_first(3);
        dll.add_first(4);
        dll.add_first(5);
        dll.add_first(6);
        dll.print();
        dll.remove_first();
        dll.print();
        dll.reverse();
        dll.print();
    }
}