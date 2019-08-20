package Sort;

public class FindKthLargest_215 {
    /*215. 数组中的第K个最大元素*/
    /*
    未排序数组找第K大元素;
    关键字第K大:堆排和快搜实现;
    快速搜素算法，时间复杂度为O（logN）;
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while(true){
            int temp = nums[left], i = left, j = right;
            while(i < j){
                while(i < j && nums[j] >= temp){
                    j--;
                }
                while(i < j && nums[i] <= temp){
                    i++;
                }
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
            nums[left] = nums[i];
            nums[i] = temp;
            if(nums.length - k > i){
                left = i + 1;
            }else if(nums.length - k == i){
                return nums[i];
            }else{
                right = i - 1;
            }
        }
    }
}
