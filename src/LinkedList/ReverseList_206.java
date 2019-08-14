package LinkedList;

public class ReverseList_206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*206.反转链表*/
    /*
    就地反转，基础题;
     */
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode res = new ListNode(-1);
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            head.next = res.next;
            res.next = head;
            head = cur;
        }
        return res.next;
    }
}
