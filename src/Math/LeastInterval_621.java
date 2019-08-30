package Math;

import java.util.Arrays;

public class LeastInterval_621 {
    /*621. 任务调度器*/
    /*
    两种情况:
    n的间距足够大时，最短时间为（最大任务数 - 1） * 每一轮间隔长度（n + 1）+ 相同最大任务数的数量;
    n的间距小，即当结果小于数组长度时，返回数组长度;
     */
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        int[] count = new int[26];
        for(char c : tasks){
            count[c - 'A']++;
        }
        Arrays.sort(count);
        int maxCount = count[count.length - 1];
        int len = 0, index = count.length - 1;
        while(index >= 0 && count[index] == maxCount){
            len++;
            index--;
        }
        return Math.max(tasks.length,(maxCount - 1) * (n + 1) + len);
    }
}
