package algo.backTracking;

import java.util.ArrayList;
import java.util.List;

public class Parentheses {

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * For example, given n = 3, a solution set is:
     *
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * A right parenthesis can only be used when there is a sparing left parenthesis before it
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n <= 0){
            return result;
        }
        backTrack(result, new StringBuilder(""), n, n);
        return result;
    }

    private void backTrack(List<String> result, StringBuilder current, int left, int right) {
        if(left == 0 && right == 0){
            result.add(current.toString());
        }
        else{
            if(left == right){
                current.append("(");
                backTrack(result, current, left - 1, right);
                current.deleteCharAt(current.length() - 1);
            }
            else{
                if(left > 0){
                    current.append("(");
                    backTrack(result, current, left - 1, right);
                    current.deleteCharAt(current.length() - 1);
                }
                if(right > 0){
                    current.append(")");
                    backTrack(result, current, left, right - 1);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }
}
