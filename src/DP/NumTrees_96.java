package DP;

public class NumTrees_96 {
    /*96. 不同的二叉搜索树*/
    /*
    递归求解会超时，用动态规划求解;
    设置初始状态0和1，只有一种情况;
    遍历填表，当前状态与前段和后段两者相乘累加得到;
     */
    public int numTrees(int n) {
        if(n == 0){
            return 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i < res.length; i++){
            for(int k = 1; k <= i; k++){
                res[i] += res[k - 1] * res[i - k];
            }
        }
        return res[n];
    }
}
