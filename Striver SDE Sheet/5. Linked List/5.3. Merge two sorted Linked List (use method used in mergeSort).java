class Optimal {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode left, ListNode right) {// T=O(n+m) S=O(1)
        ListNode prehead = new ListNode();
        ListNode current = prehead;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        if (left != null)
            current.next = left;
        else if (right != null)
            current.next = right;

        return prehead.next;
    }
}

class PersonalSolution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {// T=S=O(n+m)
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode left = list1, right = list2, head = null, current = null;

        if (left.val <= right.val) {
            current = new ListNode(left.val);
            left = left.next;
        } else {
            current = new ListNode(right.val);
            right = right.next;
        }
        head = current;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = new ListNode(left.val);
                left = left.next;
            } else {
                current.next = new ListNode(right.val);
                right = right.next;
            }
            current = current.next;
        }

        while (left != null) {
            current.next = new ListNode(left.val);
            left = left.next;
            current = current.next;
        }
        while (right != null) {
            current.next = new ListNode(right.val);
            right = right.next;
            current = current.next;
        }
        return head;
    }
}