package BinarySearch;

public class Search_81 {
    /*81. 搜索旋转排序数组 II*/
    /*
        二分查找模板;
        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
     while中不取等号，因为循环出来结果一定左右相当，再对出来的结果进行一次判断即可;
     分支条件中需要注意是否能取到边界;
     */
    public boolean search(int[] nums, int target) {
        if(nums.length == 0){
            return false;
        }
        int left = 0, right = nums.length - 1, mid;
        while(left < right){
            mid = (right - left) / 2 + left;
            if(nums[left] < nums[mid]){
                if(nums[mid] >= target && target >= nums[left]){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }else if(nums[left] > nums[mid]){
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }else{
                if(nums[left] == target){
                    return true;
                }
                left = left + 1;
            }
        }
        return nums[left] == target;
    }
}
