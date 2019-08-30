package DFS;

public class UpdateBoard_529 {
    /*529. 扫雷游戏*/
    /*
    dfs搜图;
    检查该点的周围八个方向是否有雷，若有雷则计数，停止递归;
    若没有雷则显示‘B’继续向八个方向递归;
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board,click[0],click[1]);
        return board;
    }
    public void dfs(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length){
            return;
        }
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
        }else if(board[x][y] == 'E'){
            int count = calCount(board,x,y);
            if(count == 0){
                board[x][y] = 'B';
                dfs(board,x + 1,y);
                dfs(board,x + 1,y + 1);
                dfs(board,x + 1,y - 1);
                dfs(board,x,y + 1);
                dfs(board,x,y - 1);
                dfs(board,x - 1,y);
                dfs(board,x - 1,y + 1);
                dfs(board,x - 1,y - 1);
            }else{
                board[x][y] = (char)(count + '0');
            }
        }
    }
    public int calCount(char[][] board, int x, int y){
        int count = 0;
        if(x + 1 < board.length && board[x + 1][y] == 'M'){
            count++;
        }
        if(x + 1 < board.length && y + 1 < board[0].length && board[x + 1][y + 1] == 'M'){
            count++;
        }
        if(x + 1 < board.length && y - 1 >= 0 && board[x + 1][y - 1] == 'M'){
            count++;
        }
        if(y + 1 < board[0].length && board[x][y + 1] == 'M'){
            count++;
        }
        if(y - 1 >= 0 && board[x][y - 1] == 'M'){
            count++;
        }
        if(x - 1 >= 0 && board[x - 1][y] == 'M'){
            count++;
        }
        if(x - 1 >= 0 && y + 1 < board[0].length && board[x - 1][y + 1] == 'M'){
            count++;
        }
        if(x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1] == 'M'){
            count++;
        }
        return count;
    }
}
