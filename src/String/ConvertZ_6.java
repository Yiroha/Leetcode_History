package String;

public class ConvertZ_6 {
    /*6. Z 字形变换*/
    /*
    按行打印;
    注意余数的处理，需要将下标加一;
     */
    public String convert(String s, int numRows) {
        if(s == null || numRows == 0 || numRows == 1){
            return s;
        }
        int n = numRows * 2 - 2;
        String[] strs = new String[numRows];
        for(int i = 0; i < strs.length; i++){
            strs[i] = "";
        }
        for(int i = 0; i < s.length(); i++){
            if((i + 1) % n <= numRows && (i + 1) % n != 0){
                strs[i % n] += String.valueOf(s.charAt(i));
            }else if((i + 1) % n > numRows){
                strs[numRows - 1 - ((i + 1) % n - numRows)] += String.valueOf(s.charAt(i));
            }else{
                strs[1] += String.valueOf(s.charAt(i));
            }
        }
        StringBuilder res = new StringBuilder();
        for(String str : strs){
            res.append(str);
        }
        return res.toString();
    }
}
