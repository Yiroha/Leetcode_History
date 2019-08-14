package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordBreak_139 {
    /*139.单词拆分*/
    /*
    BFS解决;
    2张HashSet分别存字典和子串（用于去重）;
    遍历list弹出字符串，当子串存在于字典时，将字符串剩余部分推入list中;
    当子串本身就是字典中的单词时，返回true;
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        LinkedList<String> list = new LinkedList<>();
        HashSet<String> sub = new HashSet<>();
        HashSet<String> word = new HashSet<>();
        for(String w : wordDict){
            word.add(w);
        }
        list.add(s);
        while(!list.isEmpty()){
            String str = list.remove();
            for(int i = 1; i <= str.length(); i++){
                String bef = str.substring(0,i);
                String last = str.substring(i);
                if(i == str.length() && word.contains(bef)){
                    return true;
                }
                if(word.contains(bef) && !sub.contains(last)){
                    sub.add(last);
                    list.add(last);
                }
            }
        }
        return false;
    }
}
