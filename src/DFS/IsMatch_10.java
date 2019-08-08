package DFS;

public class IsMatch_10 {
    /*10. 正则表达式匹配*/
    /*
    DFS解决问题;
    终结状态为字符串与正则表达式同时都遍历完成返回true，当正则表达式遍历完成而字符串没有时返回false;
    匹配正则表达式需要考虑当前字符的下一位是否为‘*’;
    当下一位为‘*’时可以匹配0个或多个字符;
     */
    public boolean isMatch(String s, String p) {
        if(s == null && p == null){
            return true;
        }
        if(s == "" || p == ""){
            return false;
        }
        return bb(s,p,0,0);
    }
    public boolean bb(String s, String p, int i, int j){
        if(i == s.length() && j == p.length()){
            return true;
        }
        if(i != s.length() && j == p.length()){
            return false;
        }
        if(j + 1 < p.length() && p.charAt(j + 1) == '*'){
            if(i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
                return bb(s,p,i + 1,j) || bb(s,p,i,j + 2);
            }else{
                return bb(s,p,i,j + 2);
            }
        }else{
            if(i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
                return bb(s,p,i + 1,j + 1);
            }
        }
        return false;
    }
}
