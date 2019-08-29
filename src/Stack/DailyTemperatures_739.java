package Stack;

import java.util.Stack;

public class DailyTemperatures_739 {
    /*739. 每日温度*/
    /*
    构建最大栈，遍历数组，当当前温度小于栈顶温度时，将当前天数压入栈;
    当前温度大于栈顶温度时，弹出栈顶天数，下标相减写入结果中;
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for(int i = 0; i < T.length; i++){
            while(!stack.isEmpty() && T[stack.peek()] < T[i]){
                int d = stack.pop();
                res[d] = i - d;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            res[stack.pop()] = 0;
        }
        return res;
    }
}
