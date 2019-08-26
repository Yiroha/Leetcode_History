package DP;

public class NumSquares_279 {
    /*279. 完全平方数*/
    /*
    动态规划思路解决，记录之前数的所需要的步数，状态转移方程为dp[i] = Math.min(dp[i],dp[i - j * j] + 1);
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            dp[i] = i;
            for(int j = 1; i - j * j >= 0; j++){
                dp[i] = Math.min(dp[i],dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
