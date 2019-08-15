package Math;

import java.util.Arrays;

public class NextGreaterElement_556 {
    /*556. 下一个更大元素 III*/
    /*
    从后往前遍历，找到第一个比后一位小的数，再从后往前遍历，找到第一个比该数大的数字;
    交换两个数字后对第一个数的后面序列进行排序;
     */
    public int nextGreaterElement(int n) {
        char[] str = String.valueOf(n).toCharArray();
        for(int i = str.length - 2; i >= 0; i--){
            if(str[i] < str[i + 1]){
                for(int j = str.length - 1; j > i; j--){
                    if(str[j] > str[i]){
                        char c = str[i];
                        str[i] = str[j];
                        str[j] = c;
                        break;
                    }
                }
                Arrays.sort(str,i + 1,str.length);
                String res = new String(str);
                if(Long.valueOf(res) < Integer.MAX_VALUE){
                    return Integer.valueOf(res);
                }
            }
        }
        return -1;
    }
}
