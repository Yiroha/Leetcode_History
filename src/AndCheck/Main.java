package AndCheck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static String getIndexAndLongest(String users, int num) {
        int maxCount = 0, maxIndex = 0;
        for(int i = 0; i < users.length(); i++){
            if(users.charAt(i) == 'b'){
                int leftLen = i - 1, rightLen = i + 1,count = 0;
                while(leftLen >= 0 && users.charAt(leftLen) == 'g'){
                    count++;
                    leftLen--;
                    if(leftLen < 0){
                        leftLen = users.length() - 1;
                    }
                }
                while(rightLen < users.length() && users.charAt(rightLen) == 'g'){
                    count++;
                    rightLen++;
                    if(rightLen == users.length() - 1){
                        rightLen = 0;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    maxIndex = i;
                }
            }
        }
        int left = 0, right = 0, gCount = 0, bCount = 0, maxBCount = 0;
        boolean flag = false;
        while(left < users.length() || (right == left) && flag){
            if(users.charAt(right) == 'b'){
                bCount++;
                maxBCount = Math.max(maxBCount,bCount);
            }
            if(users.charAt(right) == 'g'){
                gCount++;
                if(gCount > num){
                    while(gCount > num){
                        if(users.charAt(left) == 'b'){
                            bCount--;
                        }
                        if(users.charAt(left) == 'g'){
                            gCount--;
                        }
                        left++;
                    }
                }
            }
            right++;
            if(right == users.length()){
                flag = true;
                right = 0;
            }
        }
        String res = maxIndex + " " + maxBCount;
        return res;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        String _users;
        int _num;
        try {
            _users = in.nextLine();
            _num = in.nextInt();
        } catch (Exception e) {
            _users = null;
            _num = 0;
        }

        res = getIndexAndLongest(_users,_num);
        System.out.println(res);
    }
}
