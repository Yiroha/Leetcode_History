package AndCheck;

public class FindCircleNum_547 {
    /*547. 朋友圈*/
    /*
    并查集例题;
    朋友圈数初始为总人数，遍历图，每当M[i][j] == 1 且两人所属的圈子不同时人数--;
     */
    public int findCircleNum(int[][] M) {
        int len = M.length;
        if(len == 0){
            return 0;
        }
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = i;
        }
        int count = len;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                if(M[i][j] == 1){
                    int fr = find(nums,i);
                    int sr = find(nums,j);
                    if(fr != sr){
                        nums[sr] = fr;
                        count--;
                    }
                }
            }
        }
        return count;
    }
    public int find(int[] nums, int x){
        while(x != nums[x]){
            x = nums[x];
        }
        return x;
    }
}
