package Stack;

import java.util.Stack;

public class LongestValidParentheses_32 {
    /*32. 最长有效括号*/
    /*
    所有括号匹配问题首先考虑栈;
    先在栈底压入-1;
    遍历字符串:
    当遇到‘（’时将下标入栈，
    当遇到‘)’时,判断栈顶坐标对应的字符是否为‘(’，是则更新长度，否则将当前下标入栈(从此处开始新的长度);
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                if(stack.peek() != -1 && s.charAt(stack.peek()) == '('){
                    stack.pop();
                    res = Math.max(res,i - stack.peek());
                }else{
                    stack.pop();
                    stack.push(i);
                }
            }
        }
        return res;
    }
}
