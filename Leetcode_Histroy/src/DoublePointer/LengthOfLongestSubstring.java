package DoublePointer;

import java.util.HashSet;

public class LengthOfLongestSubstring {
    /*3. 无重复字符的最长子串*/;
    /*
    滑动窗口，当set中不包含右指针所指字符时right++，否则移动左指针，直至set中不包含右指针所指字符。
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null){
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0, res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(!set.contains(c)){
                set.add(c);
                right++;
            }else{
                res = Math.max(right - left,res);
                while(set.contains(c)){
                    set.remove(s.charAt(left));
                    left++;
                }
            }
        }
        res = Math.max(right - left,res);
        return res;
    }
}
