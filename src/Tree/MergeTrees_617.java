package Tree;

public class MergeTrees_617 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*617. 合并二叉树*/
    /*
    合并树，对于每个节点有三种情况，都为null，有一个为null，都不为null;
    都不为null时，创建新的节点，对左右两边分别递归调用合并方法;
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null){
            return null;
        }
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode res = new TreeNode(t1.val + t2.val);
        res.left = mergeTrees(t1.left,t2.left);
        res.right = mergeTrees(t1.right,t2.right);
        return res;
    }
}
