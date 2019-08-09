package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LetterCombinations_17 {
    /*17. 电话号码的字母组合*/
    /*
    回溯法解决;
    一张HashMap存数字与对应字母串的映射;
    终结条件为index等于电话号码的长度;
    每一步可进行的操作为选取字母串中一个字母;
    回溯过程将最后选取的字母从list中删去;
     */
    public List<String> res = new ArrayList<>();
    public HashMap<Character,String> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return res;
        }
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        bb(digits,0,new LinkedList());
        return res;
    }
    public void bb(String str, int index,LinkedList<Character> list){
        if(index == str.length()){
            StringBuilder sb = new StringBuilder();
            for(Character s : list){
                sb.append(s);
            }
            res.add(sb.toString());
            return;
        }
        String temp = map.get(str.charAt(index));
        for(int i = 0; i < temp.length(); i++){
            list.add(temp.charAt(i));
            bb(str,index + 1,list);
            list.removeLast();
        }
    }
}
