package HashMap;

import java.util.HashMap;

public class SubarraySum_560 {
    /*560. 和为K的子数组*/
    /*
    一遍遍历,和两数之和的思路相似，用一张HashMap存储累计和，连续子序列的和为sum[i] - sum[j];
     */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0, res = 0;
        for(int n : nums){
            sum += n;
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            if(!map.containsKey(sum)){
                map.put(sum,1);
            }else{
                map.put(sum,map.get(sum) + 1);
            }
        }
        return res;
    }
}
