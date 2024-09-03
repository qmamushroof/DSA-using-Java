// https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1

class Solution {
    class Node {
        int data;
        Node next;
        Node bottom;

        Node() {
        }

        Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    Node flatten(Node head) {// T=O(2nm) S=O(n)
        if (head == null || head.next == null)
            return head;
        Node mergedHead = flatten(head.next);
        return merge(head,mergedHead);
    }

    Node merge(Node l1, Node l2) {
        Node prehead = new Node();
        Node current = prehead;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                current.bottom = l1;
                current = l1;
                l1 = l1.bottom;
            } else {
                current.bottom = l2;
                current = l2;
                l2 = l2.bottom;
            }
            current.next = null;
        }
        if (l1 != null)
            current.bottom = l1;
        else if (l2 != null)
            current.bottom = l2;

        if (prehead.bottom != null)
            prehead.bottom.next = null;
        return prehead.bottom;
    }
}