package HashMap;

import java.util.HashMap;

public class TwoSum_1 {
    /*1、两数之和*/
    /*
    一遍遍历，在遍历到操作数2时，操作数1必然已经存入Map中；
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }else{
                map.put(nums[i],i);
            }
        }
        return res;
    }
}
