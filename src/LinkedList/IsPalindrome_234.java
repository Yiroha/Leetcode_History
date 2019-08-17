package LinkedList;

public class IsPalindrome_234 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*234. 回文链表*/
    /*
    用快慢指针找到链表的中点，将后半段链表反转后与前半段进行比较;
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode cur = slow.next, res = new ListNode(-1), pre;
        while(cur != null){
            pre = cur.next;
            cur.next = res.next;
            res.next = cur;
            cur = pre;
        }
        cur = res.next;
        while(cur != null){
            if(cur.val != head.val){
                return false;
            }
            cur = cur.next;
            head = head.next;
        }
        return true;
    }
}
