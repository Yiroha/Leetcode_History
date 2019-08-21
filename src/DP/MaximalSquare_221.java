package DP;

public class MaximalSquare_221 {
    /*221.最大正方形*/
    /*
    动态规划问题;
    状态转移方程为:dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]当前位置的左、上、左上三个位置的最小值 + 1;
    同时记录dp表中的最大值;
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int res = 0;
        for(int i = 1; i < matrix.length + 1; i++){
            for(int j = 1; j < matrix[0].length + 1; j++){
                if(matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }
}
