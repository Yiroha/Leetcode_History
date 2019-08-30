package String;

public class CountSubstrings_647 {
    /*647. 回文子串*/
    /*
    遍历字符串从每个字符开始检查是否为回文子串。
    分两种情况:
    以当前字符为起点;
    以当前字符和下一个相同字符为起点;
     */
    public int countSubstrings(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            int len = 0;
            while(i - len >= 0 && i + len < s.length() && s.charAt(i - len) == s.charAt(i + len)){
                res++;
                len++;
            }
            if(i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)){
                len = 0;
                while(i - len >= 0 && i + len + 1 < s.length() && s.charAt(i - len) == s.charAt(i + len + 1)){
                    res++;
                    len++;
                }
            }
        }
        return res;
    }
}
