package Stack;

import java.util.Stack;

public class DecodeString_394 {
    public String decodeString(String s) {
        if(s.equals("")){
            return "";
        }
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        String res = "";
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                num = 10 * num + s.charAt(i) - '0';
            }else if(s.charAt(i) == '['){
                numStack.add(num);
                strStack.add(res);
                num = 0;
                res = "";
            }else if(s.charAt(i) == ']'){
                int n = numStack.pop();
                String temp = strStack.pop();
                while(n > 0){
                    temp += res;
                    n--;
                }
                res = temp;
            }else{
                res += s.charAt(i);
            }
        }
        return res;
    }
}
