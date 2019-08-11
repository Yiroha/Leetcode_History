package BinarySearch;

public class FindDuplicate_287 {
    /*287. 寻找重复数*/
    /*
    对数字进行二分，而不是对索引进行二分;
    时间复杂度（NlogN）
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1, mid, count;
        while(left < right){
            mid = (right - left) / 2 + left;
            count = 0;
            for(int n : nums){
                if(n <= mid){
                    count++;
                }
            }
            if(count <= mid){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
