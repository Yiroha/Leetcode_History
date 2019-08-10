package LinkedList;

public class DeleteDuplicates_82 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*82. 删除排序链表中的重复元素 II*/
    /*
    涉及到处理头结点的问题一律创建新的节点;
    使用3个指针前节点，重复节点的头结点（慢指针），重复节点的尾节点(快指针);
    快指针移动直至null或值与慢指针指不同;判断快指针是否为慢指针的下一个节点;
    若是则说明节点不重复，反之节点重复，将快指针所指的节点接到前节点之后;
    注意判断重复直至链表尾部的情况;
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode curS = head, curF = curS.next, pre = res;
        while(curF != null){
            while(curF != null && curS.val == curF.val){
                curF = curF.next;
            }
            if(curS.next == curF){
                pre = pre.next;
                curS = curS.next;
                curF = curS.next;
            }else{
                if(curF == null){
                    pre.next = curF;
                }else{
                    pre.next = curF;
                    curS = pre.next;
                    curF = curS.next;
                }
            }
        }
        return res.next;
    }
}
