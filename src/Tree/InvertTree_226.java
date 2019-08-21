package Tree;

public class InvertTree_226 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*226. 翻转二叉树*/
    /*
    递归解决;
    交换节点的左右节点，分别递归调用左右节点;
    若根节点为null则直接返回，否则返回根节点;
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
