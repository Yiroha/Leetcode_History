package String;

public class StrStr_28 {
    /*28. 实现 strStr()*/
    /*
    暴力解法:
    遍历主字符串，逐一匹配主字符串与子串;
    若匹配失败主字符串指针前移，子串指针归0;
    优化算法:KMP算法;
    当 needle 是空字符串时我们应当返回 0 。这与Java的 indexOf() 定义相符。
     */
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0){
            return 0;
        }
        if(haystack.length() == 0){
            return -1;
        }
        int i = 0, j = 0;
        while(i < haystack.length()){
            int index = i;
            while(j < needle.length() && index < haystack.length()){
                if(haystack.charAt(index) != needle.charAt(j)){
                    break;
                }
                index++;
                j++;
            }
            if(j == needle.length()){
                return i;
            }
            j = 0;
            i++;
        }
        return -1;
    }
}
