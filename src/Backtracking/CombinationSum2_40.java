package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum2_40 {
    /*40. 组合总和 II*/
    /*
    回溯法;
    因为数组中包含重复元素，在进行枚举所有可能时需要将第二次以及后出现的重复元素跳过;
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> list = new LinkedList<>();
        backtarcking(candidates, target, list, 0);
        return res;
    }
    public void backtarcking(int[] candidates, int target, List<Integer> list, int k){
        if(target < 0){
            return;
        }else if(target == 0){
            res.add(new ArrayList<>(list));
        }else{
            for(int i = k; i < candidates.length; i++){
                if(i > k && candidates[i] == candidates[i - 1]){
                    continue;
                }
                list.add(candidates[i]);
                backtarcking(candidates, target - candidates[i], list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
