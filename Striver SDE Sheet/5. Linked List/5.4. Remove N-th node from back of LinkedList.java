// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

class Optimal {
    public class ListNode {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {// T=O(n)
        ListNode prehead = new ListNode();
        prehead.next = head;
        ListNode slow = prehead, fast = prehead;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return prehead.next;
    }
}

class PersonalSolution {
    public class ListNode {
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

    public ListNode removeNthFromEnd(ListNode head, int n) { // T=O(2n)
        if (head == null || head.next == null)
            return null;

        ListNode current = head;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }

        int idx = size - n;
        if (idx == 0)
            return head.next;

        current = head;
        while (idx > 1) {
            current = current.next;
            idx--;
        }
        current.next = current.next.next;
        return head;
    }
}