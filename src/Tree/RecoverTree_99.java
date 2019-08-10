package Tree;

import java.util.Stack;

public class RecoverTree_99 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*99. 恢复二叉搜索树*/
    /*
    是98题的拓展，恢复二叉树需要在中序遍历的序列中寻找2个排序错误的节点;
    第一个节点是降序节点的前一个节点，而第二个节点是找到第一个错误节点后的第二个降序节点;
    若完整中序遍历后仍没有找到第二个节点;则直接交换第一个节点和他的后继节点;否则交换两个降序节点;
    同样样例中存在Integer.MIN_VALUE,需要进行过滤;
     */
    public void recoverTree(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode cur = new TreeNode(Integer.MIN_VALUE);
        TreeNode p1 = null;
        TreeNode next = null;
        boolean flag = true;
        boolean first = true;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= cur.val && !first){
                if(p1 == null){
                    p1 = cur;
                    next = root;
                }else{
                    flag = false;
                    break;
                }
            }
            cur = root;
            root = root.right;
            if(first){
                first = false;
            }
        }
        if(!flag){
            int temp = p1.val;
            p1.val = root.val;
            root.val = temp;
        }else{
            int temp = p1.val;
            p1.val = next.val;
            next.val = temp;
        }
    }
}
