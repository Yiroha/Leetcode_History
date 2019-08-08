package String;

public class MyAtoi_8 {
    /*8. 字符串转换整数 (atoi)*/
    /*
    充分考虑程序的鲁棒性;
    trim()去除字符串前后多余空格;
    判断首位是否为‘+’或‘-’;
    判断剩下字符串是否是数字;
    考虑超过整数最大值或最小值的情况;
     */
    public int myAtoi(String str) {
        str = str.trim();
        if(str == null || str.length() == 0){
            return 0;
        }
        int res = 0, index = 0;
        boolean flag = false;
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            if(str.charAt(index) == '-'){
                flag = true;
            }
            index++;
        }
        if(index >= str.length() || str.charAt(index) < '0' || str.charAt(index) > '9'){
            return 0;
        }
        for(int i = index; i < str.length(); i++){
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                int num = str.charAt(i) - '0';
                if(flag){
                    if(-res < Integer.MIN_VALUE / 10 || (-res == Integer.MIN_VALUE / 10 && num >= 8)){
                        return Integer.MIN_VALUE;
                    }
                }else{
                    if(res > Integer.MAX_VALUE / 10 || ( res == Integer.MAX_VALUE / 10 && num >= 7)){
                        return Integer.MAX_VALUE;
                    }
                }
                res = res * 10 + num;
            }else{
                break;
            }
        }
        return flag ? res * -1 : res;
    }
}
