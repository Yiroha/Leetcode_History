package String;

public class LongestCommonPrefix_14 {
    /*14. 最长公共前缀*/
    /*
    水平扫描字符串数组中每一个字符串的前缀;
    其他思路:二分查找法去寻找最长前缀;
    分治法将数组中字符串分别求最长前缀;
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs[0].length() == 0){
            return "";
        }
        int index = -1;
        boolean flag = true;
        while(flag){
            index++;
            if(index >= strs[0].length()){
                break;
            }
            char c = strs[0].charAt(index);
            for(int i = 1; i < strs.length; i++){
                if(index >= strs[i].length() || c != strs[i].charAt(index)){
                    flag = false;
                    break;
                }
            }
        }
        return strs[0].substring(0,index);
    }
}
