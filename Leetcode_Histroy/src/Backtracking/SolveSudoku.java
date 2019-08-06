package Backtracking;

public class SolveSudoku {
    /*37. 解数独*/
    /*
    定义3个数组保存每行每列每格的状态，用回溯法求解。
     */
    public int[][] cols = new int[9][10];
    public int[][] rows = new int[9][10];
    public int[][] box = new int[9][10];
    public char[][] board;
    public void solveSudoku(char[][] board) {
        this.board = board;
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                char c = board[row][col];
                if(c != '.'){
                    int num = c - '0';
                    rows[row][num] = 1;
                    cols[col][num] = 1;
                    box[row / 3 * 3 + col / 3][num] = 1;
                }
            }
        }
        bb(0,0);
    }
    public boolean bb(int row, int col){
        //定义边界，结束条件
        if(col == 9){
            col = 0;
            row++;
            if(row == 9){
                return true;
            }
        }
        //尝试填空
        if(board[row][col] == '.'){
            //尝试1~9的数字
            for(int i = 1; i < 10; i++){
                boolean flag = rows[row][i] + cols[col][i] + box[row / 3 * 3 + col / 3][i] == 0;
                if(flag){
                    //改变状态
                    rows[row][i]++;
                    cols[col][i]++;
                    box[row / 3 * 3 + col / 3][i]++;
                    board[row][col] = (char)(i + '0');
                    //如果返回true则不用进行回溯
                    if(bb(row,col + 1)){
                        return true;
                    }
                    //回溯
                    rows[row][i]--;
                    cols[col][i]--;
                    box[row / 3 * 3 + col / 3][i]--;
                    board[row][col] = '.';
                }
            }
        }else{
            return bb(row,col + 1);
        }
        return false;
    }
}
