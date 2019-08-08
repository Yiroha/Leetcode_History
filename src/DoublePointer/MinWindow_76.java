package DoublePointer;

import java.util.HashMap;

public class MinWindow_76 {
    /*76. 最小覆盖子串*/
    /*
    两张HashMap,一张存子串所包含的字符及其个数，另一张表示当前子串所包含的字符及其个数；
    利用滑动窗口解题，先不断扩大右指针直至包含所有子串字符，然后不断缩小左指针并更新最小子串长度及起始坐标；
     */
    public String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0){
            return "";
        }
        HashMap<Character,Integer> dic = new HashMap<>();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if(!dic.containsKey(c)){
                dic.put(c,1);
                map.put(c,0);
            }else{
                dic.put(c,dic.get(c) + 1);
            }
        }
        int minLen = Integer.MAX_VALUE, index = 0, left = 0, right = 0, size = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(dic.containsKey(c)){
                map.put(c,map.get(c) + 1);
                /*傻狗题，样例中有大数，需要用intValue()进行‘==’的比较*/
                if(map.get(c).intValue() == dic.get(c).intValue()){
                    size++;
                }
            }
            right++;
            while(size == dic.size()){
                if(right - left < minLen){
                    minLen = right - left;
                    index = left;
                }
                c = s.charAt(left);
                if(dic.containsKey(c)){
                    map.put(c,map.get(c) - 1);
                    if(map.get(c) < dic.get(c)){
                        size--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(index,index + minLen);
    }
}
