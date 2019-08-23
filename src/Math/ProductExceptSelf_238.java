package Math;

public class ProductExceptSelf_238 {
    /*238. 除自身以外数组的乘积*/
    /*
    剑指offer原题;
    两遍遍历，第一遍处理当前数左边的乘积，第二遍处理当前数右边的乘积;
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            temp = temp * nums[i + 1];
            res[i] *= temp;
        }
        return res;
    }
}
