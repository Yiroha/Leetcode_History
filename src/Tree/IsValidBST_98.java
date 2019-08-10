package Tree;

import java.util.Stack;

public class IsValidBST_98 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*98. 验证二叉搜索树*/
    /*
    二叉搜索树的中序遍历是一个升序序列;
    用栈迭代实现中序遍历判断序列是否为升序;
    样例中有Integer.MIN_VALUE，需要在首次判断中跳过;
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        int cur = Integer.MIN_VALUE;
        boolean flag = false;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(cur >= root.val && flag){
                return false;
            }else{
                cur = root.val;
                root = root.right;
                if(!flag){
                    flag = true;
                }
            }
        }
        return true;
    }
}
