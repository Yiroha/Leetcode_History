package String;

public class CountAndSay_38 {
    /*38. 报数*/
    /*
    首先理解题意，每轮报数记录重复出现的字符个数;
    优化:尾递归
    将递归调用放在最后return处,大大减少递归的用时和消耗;
     */
    public String res = "";
    public String countAndSay(int n) {
        if(n == 0){
            return res;
        }
        return fun("1",n);
    }
    public String fun(String s, int k){
        if(k == 1){
            return s;
        }else{
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while(i < s.length()){
                int count = 1;
                while(i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)){
                    count++;
                    i++;
                }
                sb.append(count);
                sb.append(s.charAt(i));
                i++;
            }
            s = sb.toString();
        }
        return fun(s,k - 1);
    }
}
