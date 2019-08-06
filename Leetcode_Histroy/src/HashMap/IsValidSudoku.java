package HashMap;

import java.util.HashSet;

public class IsValidSudoku {
    /*36. 有效的数独*/
    /*
    HashSet存每行、每列、每格出现的数字;
    注意Box_Index = col / 3 * 3 + row / 3;
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] col = new HashSet[9];
        HashSet<Character>[] row = new HashSet[9];
        HashSet<Character>[] box = new HashSet[9];
        for(int i = 0; i < 9; i++){
            col[i] = new HashSet<>();
            row[i] = new HashSet<>();
            box[i] = new HashSet<>();
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char c = board[i][j];
                if(c != '.'){
                    if(row[i].contains(c) || col[j].contains(c) || box[i / 3 * 3 + j / 3].contains(c)){
                        return false;
                    }else{
                        row[i].add(c);
                        col[j].add(c);
                        box[i / 3 * 3 + j / 3].add(c);
                    }
                }
            }
        }
        return true;
    }
}
