package DoublePointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FindSubstring_30 {
    /*30. 串联所有单词的子串*/
    /*
    需要创建两张hashMap，一张作为字典存word及出现次数，一张存当前子串出现的word;
    双指针求解，当前word出现个数不超过字典时右指针前进，若超过则左指针前进直至word出现次数等于字典。
    注意需要以word的每个char开始一次搜索。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || words.length == 0){
            return res;
        }
        int num = words.length, len = words[0].length();
        HashMap<String,Integer> dic = new HashMap<>();
        HashMap<String,Integer> map = new HashMap<>();
        for(String str : words){
            if(dic.containsKey(str)){
                dic.put(str,dic.get(str) + 1);
            }else{
                dic.put(str,1);
                map.put(str,0);
            }
        }
        int left, right;
        for(int i = 0; i < len; i++){
            left = i;
            right = i;
            while(right <= s.length() - len){
                String word = s.substring(right,right + len);
                if(map.containsKey(word)){
                    if(map.get(word) + 1 <= dic.get(word)){
                        map.put(word,map.get(word) + 1);
                        right += len;
                        if(right - left == len * num){
                            res.add(left);
                            String temp = s.substring(left,left + len);
                            map.put(temp,map.get(temp) - 1);
                            left += len;
                        }
                    }else{
                        String temp = s.substring(left,left + len);
                        map.put(temp,map.get(temp) - 1);
                        left += len;
                    }
                }else{
                    for(String w : map.keySet()){
                        map.put(w,0);
                    }
                    right += len;
                    left = right;
                }
            }
            for(String w : map.keySet()){
                map.put(w,0);
            }
        }
        Collections.sort(res);
        return res;
    }
}
