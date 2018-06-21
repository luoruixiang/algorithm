package algo.backTracking;

import java.util.HashSet;
import java.util.Set;

public class Soduku {

    /**
     * Determine if a Sudoku is valid
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> column = new HashSet<>();
            Set<Character> cubic = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !column.add(board[j][i])) {
                    return false;
                }
                if (board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] != '.'
                        && !cubic.add(board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        else{
            solve(board, 0, 0);
        }
    }

    private boolean solve(char[][] board, int row, int column) {
        if(row == board.length){
            return true;
        }
        else if(column == board[0].length){
            return solve(board, row + 1, 0);
        }
        else{
            if(board[row][column] == '.'){
                for(char c = '1'; c <= '9'; c++){
                    if(isValid(board, row, column, c)){
                        board[row][column] = c;
                        if(solve(board, row, column + 1)){
                            return true;
                        }
                    }
                }
                board[row][column] = '.';
                return false;
            }
            else{
                return solve(board, row, column + 1);
            }
        }
    }

    private boolean isValid(char[][] board, int row, int column, char c) {
        for(int i = 0; i < 9; i++){
            if(board[row][i] == c){
                return false;
            }
            if(board[i][column] == c){
                return false;
            }
            if(board[3*(row / 3) + i / 3][3*(column / 3) + i % 3] == c){
                return false;
            }
        }
        return true;
    }
}
