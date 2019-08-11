package Math;

public class Reverse_7 {
    /*7.整数反转*/
    /*
    注意超过int的最大最小值的情况;
     */
    public int reverse(int x) {
        boolean flag = false;
        int res = 0;
        if(x < 0){
            flag = true;
        }
        while(x != 0){
            if(flag){
                if( res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && x % 10 < -8)){
                    return 0;
                }
            }else{
                if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && x % 10 > 7)){
                    return 0;
                }
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
