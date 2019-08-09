package String;

public class Multiply_43 {
    /*43. 字符串相乘*/
    /*
    创建一个整数数组用来保存每一位的结果;
    在每次计算后如果数组中的值大于9，则将十位上的数字累加到前一个下标中（首位除外）;
    注意字符串比较相等用43. 字符串相乘用equals()而不是“==”;
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        StringBuilder res = new StringBuilder();
        int[] arrayInt = new int[num1.length() + num2.length() - 1];

        for(int i = num1.length() - 1; i >= 0; i--){
            for(int j = num2.length() - 1; j >= 0; j--){
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                arrayInt[i + j] += n1 * n2;
                if(arrayInt[i + j] >= 10 && i + j != 0){
                    arrayInt[i + j - 1] += arrayInt[i + j] / 10;
                    arrayInt[i + j] = arrayInt[i + j] % 10;
                }
            }
        }
        for(int i = 0; i < arrayInt.length; i++){
            res.append(arrayInt[i]);
        }
        return res.toString();
    }
}
