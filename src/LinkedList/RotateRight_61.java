package LinkedList;

public class RotateRight_61 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*61. 旋转链表*/
    /*
    当head == null以及head.next == null 是特殊情况;
    计算链表长度，将链表头部接到链表尾部，将移动次数k取余,找到k % count的结点位置，断开链表即可;
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        int count = 1;
        ListNode cur = head;
        while(cur.next != null){
            count++;
            cur = cur.next;
        }
        cur.next = head;
        count = k % count;
        cur = head;
        while(count > 0){
            cur = cur.next;
            count--;
        }
        ListNode p = head;
        while(cur.next != head){
            cur = cur.next;
            p = p.next;
        }
        ListNode res = p.next;
        p.next = null;
        return res;
    }
}
