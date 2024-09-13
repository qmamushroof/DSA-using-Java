// https://leetcode.com/problems/copy-list-with-random-pointer/

class Solution { // O(3n)

    class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        insertCopyNodes(head);
        copyRandomConnections(head);
        return separateCopiedList(head);
    }

    public void insertCopyNodes(Node head) {
        Node current = head;
        while (current != null) {
            Node copyNode = new Node(current.val);
            copyNode.next = current.next;
            current.next = copyNode;
            current = copyNode.next;
        }
    }

    public void copyRandomConnections(Node head) {
        Node current = head, copyNode;
        while (current != null) {
            copyNode = current.next;
            if (current.random != null) {
                copyNode.random = current.random.next;
            }
            current = copyNode.next;
        }
    }

    public Node separateCopiedList(Node head) {
        Node copyHead = head.next, current = head, copyNode;
        while (current != null) {
            copyNode = current.next;
            current.next = copyNode.next;
            if (copyNode.next != null) {
                copyNode.next = copyNode.next.next;
            }
            current = current.next;
        }
        return copyHead;
    }
}
