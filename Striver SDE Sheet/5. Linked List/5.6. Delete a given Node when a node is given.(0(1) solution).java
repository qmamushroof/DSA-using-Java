// https://leetcode.com/problems/delete-node-in-a-linked-list/

class Solution {
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

    public void deleteNode(ListNode node) { // O(1)
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

class RetardedSolution {
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

    public void deleteNode(ListNode node) {
        while (node.next.next != null) {// T=O(n)
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
}