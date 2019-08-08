package HashMap;

import java.util.HashMap;

public class SingleNumber_136 {
    /*136. 只出现一次的数字*/
    /*
    HashMap存数组中元素及出现次数，一遍遍历;
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n : nums){
            int count = map.getOrDefault(n,0);
            map.put(n,count + 1);
        }
        int res = Integer.MAX_VALUE;
        for(Integer n : map.keySet()){
            if(map.get(n) == 1){
                res = n;
                break;
            }
        }
        return res;
    }
}
