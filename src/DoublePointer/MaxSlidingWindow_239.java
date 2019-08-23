package DoublePointer;

import java.util.LinkedList;

public class MaxSlidingWindow_239 {
    /*239. 滑动窗口最大值*/
    /*
    利用一个队列来解决问题，在遍历数组的过程中保持队列尾部元素是当前滑动窗口的最大值;
    把当前元素的坐标压入队列时，先将队列中小于该元素的值全部弹出;
    在滑动窗口离开队尾元素坐标时，将其移除;
    注意鲁棒性;
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || nums.length < k){
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < nums.length; i ++){
            while(!list.isEmpty() && nums[list.peek()] < nums[i]){
                list.pop();
            }
            list.push(i);
            if(i - list.get(list.size() - 1) >= k){
                list.removeLast();
            }
            if(i >= k - 1){
                res[i - k + 1] = nums[list.get(list.size() - 1)];
            }
        }
        return res;
    }
}
