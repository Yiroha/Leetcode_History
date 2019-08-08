package String;

public class LongestPalindrome_5 {
    /*5. 最长回文子串*/
    /*
    遍历一遍字符串;
    对于每个字符分两种情况讨论:
    1、以当前字符为中点判断两边字符是否相同
    2、以当前字符和下一个字符为中点判断两边字符是否相同
    记录最大子串长度时的中点和长度;
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int index = 0, len = 0, sum = 0;
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            int cur = 0;
            while(i - cur >= 0 && i + cur < s.length() && s.charAt(i - cur) == s.charAt(i + cur)){
                cur++;
            }
            cur--;
            if(cur * 2 + 1 > sum){
                index = i;
                len = cur;
                sum = cur * 2 + 1;
                flag = false;
            }
            if(i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)){
                cur = 0;
                while(i - cur >= 0 && i + 1 + cur < s.length() && s.charAt(i - cur) == s.charAt(i + 1 + cur)){
                    cur++;
                }
                cur--;
                if(cur * 2 + 2 > sum){
                    index = i;
                    len = cur;
                    sum = cur * 2 + 2;
                    flag = true;
                }
            }
        }
        return flag ? s.substring(index - len,index + 2 + len) : s.substring(index - len,index + 1 + len);
    }
}
