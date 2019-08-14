package DP;

public class Jump_45 {
    /*45. 跳跃游戏 II*/
    /*
    动态规划的时间复杂度为O(n^2)会超时，需要对其优化剪枝;
    从当前位置能到达的最大位置开始:
    如果该位置能够达到终点则直接返回,
    如果该位置之前没有到达过则将其值赋为当前count + 1;
    除此之外直接返回，因为之前到过了的位置，现在必然不是最优解;
     */
    public int jump(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return 0;
        }
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = nums[i]; j > 0; j--){
                if(i + j >= nums.length - 1){
                    return dp[i] + 1;
                }else if(dp[i + j] == 0){
                    dp[i + j] = dp[i] + 1;
                }else{
                    break;
                }
            }
        }
        return 0;
    }
}
