package DoublePointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagrams_438 {
    /*438. 找到字符串中所有字母异位词*/
    /*
    需要创建两张hashMap，一张作为字典存子串字符及其出现次数，一张存当前子串出现的字符;
    双指针求解，当前map与字典元素相同时将左指针加入结果集，删去左指针元素，右指针前进一，加入所指元素;
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        HashMap<Character,Integer> dic = new HashMap<>();
        HashMap<Character,Integer> map = new HashMap<>();
        int len = p.length();
        for(int i = 0; i < len; i++){
            char c = p.charAt(i);
            if(dic.containsKey(c)){
                dic.put(c,dic.get(c) + 1);
            }else{
                dic.put(c,1);
            }
        }
        int left = 0 , right = len - 1;
        for(int i = 0; i < len - 1; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c) + 1);
            }else{
                map.put(c,1);
            }
        }
        while(right < s.length()){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                map.put(c,map.get(c) + 1);
            }else{
                map.put(c,1);
            }
            if(fun(dic,map)){
                res.add(left);
            }
            c = s.charAt(left);
            if(map.get(c).equals(1)){
                map.remove(c);
            }else{
                map.put(c,map.get(c) - 1);
            }
            left++;
            right++;
        }
        return res;
    }

    public boolean fun(HashMap<Character,Integer> map1, HashMap<Character,Integer> map2){
        for(Map.Entry<Character,Integer> entry : map1.entrySet()){
            if(map2.containsKey(entry.getKey())){
                if(!map2.get(entry.getKey()).equals(entry.getValue())){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }
}
