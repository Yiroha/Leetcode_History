package com.per.iroha.imsystem;
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] sub = in.nextLine().split(",");
        HashSet<String> hashSet = new HashSet<>();
        Collections.addAll(hashSet,sub);
        System.out.println(moveSubstr(str,hashSet));
//        for(int i = 0; i < sub.length; i++){
//            str.replaceAll(sub[i],"");
//        }
//        System.out.println(str.length());
    }

    private static int moveSubstr(String str, HashSet<String> set)
    {
        if(str.length() == 0){
            return 0;
        }
        int minLen = str.length();
        HashSet<String> res = new HashSet<>();
        LinkedList<String> list = new LinkedList<>();
        list.add(str);

        while(!list.isEmpty()){
            String s = list.poll();
            for(String sub : set){
                int pos = s.indexOf(sub);
                while (pos != -1){
                    String temp = s.substring(0,pos) + s.substring(pos + sub.length());
                    if(!res.contains(temp)){
                        list.add(temp);
                        res.add(temp);
                        minLen = Math.min(minLen,temp.length());
                    }
                    pos = s.indexOf(sub,pos + 1);
                }
            }
        }
        return minLen;
    }
}
