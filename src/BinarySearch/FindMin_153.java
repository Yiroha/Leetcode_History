package BinarySearch;

public class FindMin_153 {
    /*153. 寻找旋转排序数组中的最小值*/
    /*
    二分查找法，分三种情况;
    在旋转数组中，mid落在旋转点前序列中，最小值在mid右侧;
    在旋转数组中，mid落在旋转点后的序列中，最小值在mid左侧;
    在升序数组中，最小值在mid左侧;
     */
    public int findMin(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int left = 0, right = nums.length - 1, mid;
        while(left < right){
            mid = left + (right - left) / 2;
            if(nums[mid] >= nums[left] && nums[left] > nums[right]){
                left = mid + 1;
            }else if(nums[mid] > nums[left] && nums[left] < nums[right]){
                right = mid - 1;
            }else{
                right = mid;
            }
        }
        return nums[left];
    }
}
