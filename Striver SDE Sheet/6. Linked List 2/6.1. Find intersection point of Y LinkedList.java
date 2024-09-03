// https://leetcode.com/problems/intersection-of-two-linked-lists/

import java.util.Set;
import java.util.HashSet;

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

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) { // T=S=O(m+n)
        ListNode currA = headA, currB = headB;
        Set<ListNode> nodeSet = new HashSet<>();

        while (currB != null) {
            nodeSet.add(currB);
            currB = currB.next;
        }

        while (currA != null) {
            if (nodeSet.contains(currA))
                return currA;
            currA = currA.next;
        }

        return null;
    }
}