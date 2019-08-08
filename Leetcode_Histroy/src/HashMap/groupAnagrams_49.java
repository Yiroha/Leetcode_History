package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class groupAnagrams_49 {
    /*49. 字母异位词分组*/
    /*
    一张HashMap存字典顺序的字符串及结果列表list;
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length == 0){
            return res;
        }
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(String s : strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String temp = new String(chars);
            if(!map.containsKey(temp)){
                map.put(temp,new ArrayList());
            }
            map.get(temp).add(s);
        }
        for(String s : map.keySet()){
            res.add(map.get(s));
        }
        return res;
    }
}
