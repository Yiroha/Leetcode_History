package Stack;

import java.util.Stack;

public class FindUnsortedSubarray_581 {
    /*581. 最短无序连续子数组*/
    /*
    用栈从数组的两端开始遍历，寻找数组中排序错误的最小/最大元素本该在的位置;
     */
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length - 1, right = 0;
        for(int i = 0; i < nums.length; i++){
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                left = Math.min(left,stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for(int i = nums.length - 1; i >= 0; i--){
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                right = Math.max(right,stack.pop());
            }
            stack.push(i);
        }
        return right > left ? right - left + 1 : 0;
    }
}
