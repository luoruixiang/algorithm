package algo.backTracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        boolean[] columnRecord = new boolean[n];
        backTrack(board, result, n, columnRecord, 0);
        return result;
    }

    private void backTrack(char[][] board,
                           List<List<String>> result,
                           int leftQueens,
                           boolean[] columnRecord,
                           int rowIndex) {
        if(leftQueens == 0){
            result.add(build(board));
            return;
        }
        else{
            for(int column = 0; column < board[0].length; column++){
                if(!columnRecord[column] && isValidDiagonal(rowIndex, column, board)){
                    board[rowIndex][column] = 'Q';
                    columnRecord[column] = true;
                    backTrack(board, result, leftQueens - 1, columnRecord, rowIndex + 1);
                    board[rowIndex][column] = '.';
                    columnRecord[column] = false;
                }
            }
        }
    }

    private boolean isValidDiagonal(int rowIndex, int columnIndex, char[][] board) {
        int row = rowIndex;
        int column = columnIndex;
        //larger than rowIndex' cells are all not filled.
        while(row > 0 && column > 0){
            if(board[--row][--column] == 'Q'){
                return false;
            }
        }
        row = rowIndex;
        column = columnIndex;
        while(row > 0 && column < board[0].length - 1){
            if(board[--row][++column] == 'Q'){
                return false;
            }
        }
        return true;
    }

    private List<String> build(char[][] board) {
        List<String> result = new ArrayList<>();
        for(char[] row : board){
            result.add(String.valueOf(row));
        }
        return result;
    }
}
