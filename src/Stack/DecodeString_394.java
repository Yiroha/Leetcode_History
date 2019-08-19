package Stack;

import java.util.Stack;

public class DecodeString_394 {
    /*394. 字符串解码*/
    /*
    两个栈分别存[]前的数字和之前的字符串;
    遍历一遍输入的字符串，遇到数字和字符进行记录，遇到"["将之前的字符串和记录的数字压入栈;
    遇到"]"弹出当前括号的数字N以及之前的字符串，将当前括号中的字符串重复N次接到之前字符串之后。
     */
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
