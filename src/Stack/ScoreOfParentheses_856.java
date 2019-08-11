package Stack;

import java.util.Stack;

public class ScoreOfParentheses_856 {
    /*856.括号的分数*/
    /*
    括号问题一律用栈解决;
    栈底压入0作为最终结果;
    当压入‘（’时判断下一位是否为‘）’若是压入1，否则压入0;
    当压入‘）’时判断上一位是否为‘(’若是将栈顶元素加一，否则栈顶元素乘2;
     */
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == '('){
                if(S.charAt(i + 1) == ')'){
                    stack.push(1);
                }else{
                    stack.push(0);
                }
            }else{
                int num = stack.pop();
                if(num == 1 && S.charAt(i - 1) == '('){
                    stack.push(stack.pop() + 1);
                }else{
                    num *= 2;
                    stack.push(stack.pop() + num);
                }
            }
        }
        return stack.pop();
    }
}
