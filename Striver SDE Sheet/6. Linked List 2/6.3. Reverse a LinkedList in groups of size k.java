// https://leetcode.com/problems/reverse-nodes-in-k-group/

class Solution {
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

    ListNode reverseKGroup(ListNode head, int k) { // O(2n)
        if (head == null || head.next == null || k <= 1)
            return head;

        ListNode current = head, previousTail = null;

        while (current != null) {
            ListNode kthNode = getKthNode(current, k);
            if (kthNode == null) {
                if (previousTail != null)
                    previousTail.next = current;
                break;
            }

            ListNode afterKth = kthNode.next;
            kthNode.next = null;

            reverseList(current);

            if (head == current)
                head = kthNode;
            else
                previousTail.next = kthNode;

            previousTail = current;
            current = afterKth;
        }
        return head;
    }

    ListNode getKthNode(ListNode current, int k) {
        while (k > 1 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

    ListNode reverseList(ListNode current) {
        ListNode previous = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}