package Math;

public class SearchMatrix_240 {
    /*240. 搜索二维矩阵 II*/
    /*
    剑指offer原题，思路是将起始坐标放在右上角，保证比目标值小的数一点在左边，比目标值大的数一定在右边;
    每次判断与目标值大小关系时向左向下移动，当在边界值时返回false;
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while(matrix[i][j] != target){
            if(matrix[i][j] > target){
                j--;
                if(j < 0){
                    return false;
                }
            }
            if(matrix[i][j] < target){
                i++;
                if(i >= matrix.length){
                    return false;
                }
            }
        }
        return true;
    }
}
