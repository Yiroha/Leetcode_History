package Sort;

import java.util.*;

public class TopKFrequent_347 {
    /*347. 前 K 个高频元素*/
    /*
    TopK问题，HashMap统计词频，构建小顶堆存储最大的K个词;
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else{
                map.put(nums[i],map.get(nums[i]) + 1);
            }
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for(Integer n : map.keySet()){
            heap.add(n);
            if(heap.size() > k){
                heap.poll();
            }
        }
        for(Integer n : heap){
            res.add(n);
        }
        Collections.reverse(res);
        return res;
    }
}
