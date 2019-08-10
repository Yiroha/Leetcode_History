package Tree;

import java.util.ArrayList;
import java.util.List;

public class GenerateTrees_95 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*95. 不同的二叉搜索树 II*/
    /*
    递归求解;
    例1.2.3.4.5，中以2为根节点，可将节点分为1和3.4.5两个部分;
    分别遍历两者中的节点，接到当前节点的左右上;
    终结条件为left > right，此时节点集为"";
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        return fun(1,n);
    }
    public List<TreeNode> fun(int left, int right){
        List<TreeNode> list = new ArrayList<>();
        if(left > right){
            list.add(null);
            return list;
        }
        if(left == right){
            list.add(new TreeNode(left));
            return list;
        }
        for(int k = left; k <= right; k++){
            List<TreeNode> leftNode = fun(left,k - 1);
            List<TreeNode> rightNode = fun(k + 1,right);
            for(int i = 0; i < leftNode.size(); i++){
                for(int j = 0; j < rightNode.size(); j++){
                    TreeNode root = new TreeNode(k);
                    root.left = leftNode.get(i);
                    root.right = rightNode.get(j);
                    list.add(root);
                }
            }
        }
        return list;
    }
}
