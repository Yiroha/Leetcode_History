package Tree;

public class PathSum_437 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*437. 路径总和 III*/
    /*
    递归解决;
    两类情况，以当前节点重新开始计算，继承上一个节点的目标值进行累计;
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        return fun(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);
    }
    public int fun(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int res = 0;
        if(sum == root.val){
            res++;
        }
        return res + fun(root.left,sum - root.val) + fun(root.right,sum - root.val);
    }
}
