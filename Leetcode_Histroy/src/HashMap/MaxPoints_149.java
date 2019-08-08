package HashMap;

import java.util.HashMap;

public class MaxPoints_149 {
    /*149. 直线上最多的点数*/
    /*
    HashMap来保存斜率以及相同斜率的点数;
    注意斜率为0有正零和负零的区别;
    考虑除数为0的情况;
    当出现相同点时另外计数;
     */
    public int maxPoints(int[][] points) {
        if(points.length == 0 || points.length == 1){
            return points.length;
        }
        HashMap<Double,Integer> map = new HashMap<>();
        int res = 0, cur = 1, same = 0;
        for(int i = 0; i < points.length - 1; i++){
            for(int j = i + 1; j < points.length; j++){
                double d;
                if(points[i][0] - points[j][0] == 0 && points[i][1] - points[j][1] == 0){
                    same++;
                }else{
                    if(points[i][0] == points[j][0]){
                        d = 0;
                    }else if(points[i][1] - points[j][1] != 0){
                        d = (double)(points[i][0] - points[j][0]) / (points[i][1] - points[j][1]);
                    }else{
                        d = points[i][1];
                    }
                    if(!map.containsKey(d)){
                        map.put(d,2);
                    }else{
                        map.put(d,map.get(d) + 1);
                    }
                }
            }
            for(Double d : map.keySet()){
                if(map.get(d) > cur){
                    cur = map.get(d);
                }
            }
            res = Math.max(res,cur + same);
            cur = 1;
            same = 0;
            map.clear();
        }
        return res;
    }
}
