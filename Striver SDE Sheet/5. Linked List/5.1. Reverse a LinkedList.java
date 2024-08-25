// https://leetcode.com/problems/reverse-linked-list/

class Solution {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node reverseList(Node head) { // T=O(n) S=O(1)
        Node current = head, previous = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}