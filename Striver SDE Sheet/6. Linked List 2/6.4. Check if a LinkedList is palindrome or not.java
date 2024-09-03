// https://leetcode.com/problems/palindrome-linked-list/

class Solution {// T=O(2n)=O(n) S=O(1)
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

    boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = get1stMiddleNode(head);// O(n/2)

        ListNode left = head, right = mid.next;

        right = reverseList(right);// O(n/2)

        boolean isPalindrome = isEqual(left, right);// O(n/2)

        // The reversed part is re-reversed to maintain the original LinkedList
        reverseList(right);// O(n/2)

        return isPalindrome;
    }

    ListNode get1stMiddleNode(ListNode head) {// O(n/2)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == null || fast.next == null)
                return slow;
            slow = slow.next;
        }
        return slow;
    }

    ListNode reverseList(ListNode head) {// O(n)
        if (head == null || head.next == null)
            return head;
        ListNode previous = null, current = head, next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    boolean isEqual(ListNode left, ListNode right) {// O(n)
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }
}