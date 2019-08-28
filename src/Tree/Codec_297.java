package Tree;

import java.util.LinkedList;

public class Codec_297 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*297. 二叉树的序列化与反序列化*/
    /*
    序列化与反序列化都由层序遍历实现;
    反序列化利用两个指针，一个指向父节点，一个指向子节点;
    序列化时字符串操作用StringBuilder 否则会超时;
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder("");
        if(root == null){
            return "";
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()){
            int size = list.size();
            while(size-- > 0){
                root = list.pop();
                if(root != null){
                    list.add(root.left);
                    list.add(root.right);
                    res.append(",");
                    res.append(root.val);
                }else{
                    res.append(",NULL");
                }
            }
        }
        res.deleteCharAt(0);
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0){
            return null;
        }
        String[] str = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(str[0]));
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int rootIndex = 0;
        int valueIndex = 1;
        while(rootIndex < list.size()){
            TreeNode node = list.get(rootIndex++);
            if(valueIndex < str.length){
                if(str[valueIndex].equals("NULL")){
                    node.left = null;
                }else{
                    node.left = new TreeNode(Integer.valueOf(str[valueIndex]));
                }
                valueIndex++;
                if(str[valueIndex].equals("NULL")){
                    node.right = null;
                }else{
                    node.right = new TreeNode(Integer.valueOf(str[valueIndex]));
                }
                valueIndex++;
            }
            if(node.left != null){
                list.add(node.left);
            }
            if(node.right != null){
                list.add(node.right);
            }
        }
        return root;
    }
}
