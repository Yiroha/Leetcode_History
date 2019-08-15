package String;

public class CountBinarySubstrings_696 {
    /*696. 计数二进制子串*/
    /*
    一遍遍历，思路:
    计重复数的长度，与前一个数长度比较，取最小值加到结果上;
     */
    public int countBinarySubstrings(String s) {
        int cur = 1, pre = 0, index = 0, res = 0;
        while(index < s.length()){
            while(index + 1 < s.length() && s.charAt(index) == s.charAt(index + 1)){
                cur++;
                index++;
            }
            res += Math.min(pre,cur);
            pre = cur;
            cur = 1;
            index++;
        }
        return res;
    }
}
