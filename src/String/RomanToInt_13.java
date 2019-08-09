package String;

import java.util.HashMap;

public class RomanToInt_13 {
    /*13. 罗马数字转整数*/
    /*
    一张HashMap存罗马数字与整数的映射;
    遍历一遍字符串，分两种情况:
    1、存在2个罗马数字为一组代表一个整数的特殊情况
    2、单个罗马数字代表一个整数
     */
    public int romanToInt(String s) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);
        int res = 0, index = 0;
        while(index < s.length()){
            if(index + 1 < s.length() && map.containsKey(s.substring(index,index + 2))){
                res += map.get(s.substring(index,index + 2));
                index += 2;
            }else{
                res += map.get(s.substring(index,index + 1));
                index += 1;
            }
        }
        return res;
    }
}
