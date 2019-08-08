package com.per.iroha.imsystem;

import java.util.Scanner;

public class D {
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            int c;
            int k;
            int v;
            int num;
            while(in.hasNextInt()){
                c = in.nextInt();
                num = in.nextInt();
                int[] a = new int[c];
                int[] b = new int[c];
                for(int i = 0; i < c; i++){
                    v = in.nextInt();
                    a[i] = v;
                }
                while (num-- > 0) {
                    int com = in.nextInt();
                    if(com == 1){
                        k = in.nextInt();
                        System.out.println(b[k - 1]);
                    }else{
                        k = in.nextInt();
                        v = in.nextInt();
                        for(int i = k - 1; i < c; i++){
                            if(b[i] < a[i]){
                                int d = a[i] - b[i];
                                if(v <= d){
                                    b[i] += v;
                                    break;
                                }else{
                                    b[i] = a[i];
                                    v -= d;
                                }
                            }
                        }
                    }
                }
            }
        }
}
