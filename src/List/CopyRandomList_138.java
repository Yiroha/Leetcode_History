package List;

public class CopyRandomList_138 {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
    /*138. 复制带随机指针的链表*/
    /*
    3遍遍历，第一遍将所有节点复制一份接在原节点之后;
    第二遍遍历复制random指向的的节点;
    第三遍遍历将新旧链表分开;
     */
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node cur = head;
        while(cur != null){
            Node copyNode = new Node();
            copyNode.val = cur.val;
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = cur.next.next;
        }
        cur = head;
        while(cur != null){
            if(cur.random != null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head;
        Node res = new Node();
        Node p = res;
        while(cur != null){
            p.next = cur.next;
            cur.next = p.next.next;
            cur = cur.next;
            p = p.next;
        }
        return res.next;
    }
}
