package Tree;

public class LowestCommonAncestor_236 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*236. 二叉树的最近公共祖先*/
    /*
    两种思路:递归遍历，Map存储节点的所有父节点;
    递归求解判断，当前节点或他的左右节点是否包含目标节点，若都包含则输出结果，包含一个则返回给父节点判断父节点是否成立;
     */
    public TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        fun(root,p,q);
        return res;
    }
    public boolean fun(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        int left = fun(root.left,p,q) ? 1 : 0;
        int right = fun(root.right,p,q) ? 1 : 0;
        int mid = (root == p || root == q) ? 1 : 0;
        if(left + right + mid >= 2){
            res = root;
        }
        return left + right + mid > 0;
    }
}
