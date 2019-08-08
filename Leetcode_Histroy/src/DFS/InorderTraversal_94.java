package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal_94 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*94. 二叉树的中序遍历*/
    /*
    中序遍历，基础;
    迭代用栈实现;
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
