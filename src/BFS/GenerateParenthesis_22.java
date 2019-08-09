package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GenerateParenthesis_22 {
    /*22. 括号生成*/
    /*
    BFS类型问题用队列解决;
    队列分别用于存储字符串，左括号数量，右括号数量;
    终结条件为左右括号数同时等于目标值;
    当左括号小于目标值时允许添加左括号;
    右括号小于左括号数量时允许添加右括号;
    注意两个操作初始条件保持相同;
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        LinkedList<String> list = new LinkedList<>();
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        list.add("");
        left.add(0);
        right.add(0);
        while(!list.isEmpty()){
            String str = list.remove();
            int l = left.remove();
            int r = right.remove();
            if(l == n && r == n){
                res.add(str);
            }else{
                if(l < n){
                    String temp = str + '(';
                    if(!set.contains(temp)){
                        set.add(temp);
                        list.add(temp);
                        left.add(l + 1);
                        right.add(r);
                    }
                }
                if(r < l){
                    String temp = str + ')';
                    if(!set.contains(temp)){
                        set.add(temp);
                        list.add(temp);
                        left.add(l);
                        right.add(r + 1);
                    }
                }
            }
        }
        return res;
    }
}
