// https://leetcode.com/problems/rotate-list/description/

class Solution { // O(2n)
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) { // O(2n)
        if (head == null || head.next == null || k == 0)
            return head;

        ListNode current = head;
        int size = 1;
        while (current.next != null) {
            current = current.next;
            size++;
        }
        current.next = head;

        int rotations = k % size;
        int newHeadIdx = size - rotations;
        current = head;
        while (newHeadIdx > 1) {
            current = current.next;
            newHeadIdx--;
        }
        head = current.next;
        current.next = null;

        return head;
    }
}

class PersonalSolution { // O(2n)
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) { // O(2n)
        if (head == null || head.next == null || k == 0)
            return head;

        int size = getSize(head);

        int rotations = k % size;
        if (rotations == 0)
            return head;

        int idx = size - rotations;
        ListNode current = head;
        while (idx > 1) {
            current = current.next;
            idx--;
        }
        ListNode newHead = current.next;
        current.next = null;

        current = newHead;
        while (current != null && current.next != null)
            current = current.next;
        if (current != null)
            current.next = head;

        return newHead;
    }

    int getSize(ListNode head) {
        ListNode current = head;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }
}