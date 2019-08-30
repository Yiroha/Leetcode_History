package Tree;

import java.util.Stack;

public class ConvertBST_538 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*538. 把二叉搜索树转换为累加树*/
    /*
    从右节点开始进行先序遍历;
    对每个节点加上累加值，同时记录新的累加值;
     */
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return null;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode res = root;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root.val += count;
            count = root.val;
            root = root.left;
        }
        return res;
    }
}
