package Stack;

import java.util.Stack;

public class MaximalRectangle_85 {
    /*85. 最大矩形*/
    /*
    将矩阵视为多个求最大柱状面积的子问题;
    用单调栈解决子问题，求子问题的最大值;
    注意出栈时，面积为栈顶坐标的高度 * 栈顶下一个坐标到当前坐标的距离 - 1；
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        int[] num = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    num[j]++;
                }else{
                    num[j] = 0;
                }
                while(stack.peek() != -1 && num[j] < num[stack.peek()]){
                    res = Math.max(res,num[stack.pop()] * (j - stack.peek() - 1));
                }
                stack.push(j);
            }
            while(stack.peek() != -1){
                res = Math.max(res,num[stack.pop()] * (matrix[0].length - stack.peek() - 1));
            }
        }
        return res;
    }
}
