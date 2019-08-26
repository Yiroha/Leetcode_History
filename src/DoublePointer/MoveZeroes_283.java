package DoublePointer;

public class MoveZeroes_283 {
    /*283. 移动零*/
    /*
    将交换过程拆成2步，利用快慢指针将非零数移至数组头部，再将数组剩余部分置零;
     */
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length){
            if(nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while(slow < nums.length){
            nums[slow] = 0;
            slow++;
        }
    }
}
