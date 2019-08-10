package LinkedList;

public class SwapPairs_24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*24. 两两交换链表中的节点*/
    /*
    涉及到头结点的问题一律在头结点之前创建一个新的节点;
    链表操作时注意保存第二个节点的next;
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        ListNode af, temp;
        res.next = head;
        while(cur.next != null && cur.next.next != null){
            af = cur.next.next.next;
            temp = cur.next;
            cur.next = cur.next.next;
            cur.next.next = temp;
            temp.next = af;
            cur = cur.next.next;
        }
        return res.next;
    }
}
