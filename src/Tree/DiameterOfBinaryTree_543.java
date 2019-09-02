package Tree;

public class DiameterOfBinaryTree_543 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*543. 二叉树的直径*/
    /*
    每次递归检查节点的左右节点长度之和是否最大，并返回当前节点的深度;
    最后在根节点验证根节点的左右节点长度之和是否最大，返回结果;
     */
    public int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = fun(root.left);
        int right = fun(root.right);
        res = Math.max(res,left + right);
        return res;
    }
    public int fun(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = fun(root.left);
        int right = fun(root.right);
        int deep = Math.max(left,right);
        res = Math.max(res,left + right);
        return 1 + deep;
    }
}
