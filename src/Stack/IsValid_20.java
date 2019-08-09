package Stack;

import java.util.HashMap;
import java.util.Stack;

public class IsValid_20 {
    /*20. 有效的括号*/
    /*
    所有括号匹配问题首先考虑栈;
    用HashMap存储左右括号的映射;
    用右括号满足栈顶元素，并且遍历结束时栈为空，则括号有效;
     */
    public boolean isValid(String s) {
        if(s.length() == 0){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }else{
                if(!stack.isEmpty() && s.charAt(i) == map.get(stack.peek())){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
