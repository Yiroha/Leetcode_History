package DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FourSum {
    /*四数之和*/
    /*
    先固定2个位置的数，然后用双指针求解，结果需要用HashSet去重;
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 4){
            return res;
        }
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        int left1 = 0, left2 = left1 + 1, left3 = left2 + 1, right = nums.length - 1;
        while(left1 <= nums.length - 4){
            while(left2 <= nums.length - 3){
                while(left3 < right){
                    int sum = nums[left1] + nums[left2] + nums[left3] + nums[right];
                    if(sum == target){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[left1]);
                        list.add(nums[left2]);
                        list.add(nums[left3]);
                        list.add(nums[right]);
                        if(!set.contains(list)){
                            res.add(list);
                            set.add(list);
                        }
                        left3++;
                    }else if(sum > target){
                        right--;
                    }else{
                        left3++;
                    }
                }
                left2++;
                left3 = left2 + 1;
                right = nums.length - 1;
            }
            left1++;
            left2 = left1 + 1;
            left3 = left2 + 1;
            right = nums.length - 1;
        }
        return res;
    }
}
