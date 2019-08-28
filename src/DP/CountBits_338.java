package DP;

public class CountBits_338 {
    /*338. 比特位计数*/
    /*
    动态规划:
    由于偶数二进制末尾是0;奇数二进制末尾是1;
    当前数的1的数量为右移一位后的数加上末尾的1;
     */
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for(int i = 1; i <= num; i++){
            dp[i] = dp[i >> 1] + i % 2;
        }
        return dp;
    }
}
