package Math;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers_448 {
    /*448. 找到所有数组中消失的数字*/
    /*
    不使用额外空间，通过交换将将数组的下标与其值一一对应;最后遍历一遍数组，将不对应的下标输出到结果;
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res= new ArrayList<>();
        if(nums.length == 0){
            return res;
        }
        int index = 0;
        while(index < nums.length){
            if(nums[index] == index + 1 || nums[index] == nums[nums[index] - 1]){
                index++;
            }else{
                int temp = nums[index];
                nums[index] = nums[nums[index] - 1];
                nums[temp - 1] = temp;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                res.add(i + 1);
            }
        }
        return res;
    }
}
