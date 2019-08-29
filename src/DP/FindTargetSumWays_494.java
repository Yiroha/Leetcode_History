package DP;

public class FindTargetSumWays_494 {
    /*494. 目标和*/
    /*
    对于选取数组中的数有两种选择一个是加一个是减，将数组之和平移，将问题转化为取或不取当前数，即01背包问题;
    初始条件为dp[0] = 1;
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum < S || -sum > S){
            return 0;
        }
        int[] dp = new int[sum * 2 + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = sum * 2; j >= nums[i] * 2; j--){
                dp[j] += dp[j - nums[i] * 2];
            }
        }
        return dp[sum + S];
    }
}
