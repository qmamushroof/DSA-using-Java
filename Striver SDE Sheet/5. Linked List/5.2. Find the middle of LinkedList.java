// https://leetcode.com/problems/middle-of-the-linked-list/

class SuperOptimal {
    class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public ListNode middleNode(ListNode head) { // T=O(n/2) S=O(1)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode firstMiddleNode(ListNode head) { // T=O(n/2) S=O(1)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // Move the fast pointer first
            if (fast == null || fast.next == null) { // If fast reaches the end, return slow
                return slow;
            }
            slow = slow.next; // Move the slow pointer after checking the fast pointer
        }
        return slow;
    }
    
}

class PersonalSolution {
    class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public ListNode middleNode(ListNode head) { // T=O(n+n/2) S=O(1)
        int size = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            size++;
        }

        int mid = size / 2;
        current = head;
        while (mid > 0) {
            current = current.next;
            mid--;
        }
        return current;
    }
}