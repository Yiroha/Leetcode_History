package Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permute_46 {
    /*46. 全排列*/
    /*
    全排列回溯法的例题;
    终结条件为索引等于字符串长度，将当前字符串写入结果中;
    执行的分支为，当前字符与之后的字符换位，然后进行下一层，分支执行完之后，再将字符换回来;
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0){
            return res;
        }
        List<Integer> list = new ArrayList<>();
        for(int n : nums){
            list.add(n);
        }
        bb(list, 0);
        return res;
    }
    public void bb(List<Integer> list, int index){
        if(index == list.size()){
            res.add(new ArrayList(list));
            return;
        }
        for(int i = index; i < list.size(); i++){
            Collections.swap(list,index,i);
            bb(list,index + 1);
            Collections.swap(list,index,i);
        }
    }
}
