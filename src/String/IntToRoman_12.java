package String;

import java.util.LinkedHashMap;

public class IntToRoman_12 {
    /*12. 整数转罗马数字*/
    /*
    用一张LinkedHashMap来存整数与罗马数字的映射，需要保证降序输入的顺序;
    通过贪心算法，每一步选取尽可能大的面值;
     */
    public String intToRoman(int num) {
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
        map.put(1000,"M");
        map.put(900,"CM");
        map.put(500,"D");
        map.put(400,"CD");
        map.put(100,"C");
        map.put(90,"XC");
        map.put(50,"L");
        map.put(40,"XL");
        map.put(10,"X");
        map.put(9,"IX");
        map.put(5,"V");
        map.put(4,"IV");
        map.put(1,"I");

        StringBuilder res = new StringBuilder();
        for(Integer n : map.keySet()){
            while(num >= n){
                res.append(map.get(n));
                num -= n;
            }
        }
        return res.toString();
    }
}
