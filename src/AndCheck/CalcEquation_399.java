package AndCheck;

import java.util.HashMap;
import java.util.List;

public class CalcEquation_399 {
    /*399. 除法求值*/
    /*
    带权重的并查集;
    用一张Map存储String及对应的节点;
    遍历equations建立并查集;计算比值，将被除数这边的树等比放大;
    遍历queries输出结果，当除号两边字符的根节点不同或除号两边字符没有出现则输出-1.0;
     */
    public class Node{
        private Node parent;
        public double val;
        private Node(double val){
            this.val = val;
            parent = this;
        }
    }
    private Node find(Node n){
        while(n != n.parent){
            n = n.parent;
        }
        return n;
    }
    private void union(Node n1, Node n2, double num){
        Node p1 = find(n1);
        Node p2 = find(n2);
        double r = n2.val * num / n1.val;
        for(String str : map.keySet()){
            Node n = map.get(str);
            if(find(n) == p1){
                n.val *= r;
            }
        }
        p1.parent = p2;
    }
    public HashMap<String,Node> map = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        for(int i = 0; i < equations.size(); i++){
            String f = equations.get(i).get(0);
            String s = equations.get(i).get(1);
            if(!map.containsKey(f)){
                map.put(f,new Node(values[i]));
            }
            if(!map.containsKey(s)){
                map.put(s,new Node(1.0));
            }
            union(map.get(f),map.get(s),values[i]);
        }
        for(int i = 0; i < queries.size(); i++){
            String f = queries.get(i).get(0);
            String s = queries.get(i).get(1);
            if(!map.containsKey(f) || !map.containsKey(s) || find(map.get(f)) != find(map.get(s))){
                res[i] = -1.0;
            }else{
                res[i] = map.get(f).val / map.get(s).val;
            }
        }
        return res;
    }
}
