package algo.backTracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0 || k == 0){
            return result;
        }
        List<Integer> current = new ArrayList<>();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = i + 1;
        }
        backtrack(array, result, current, k, 0);
        return result;
    }

    private void backtrack(int[] candidates, List<List<Integer>> result, List<Integer> current, int k, int startstartIndex) {
        if(k == 0){
            result.add(new ArrayList<>(current));
        }
        else{
            for(int currentIndex = startstartIndex; currentIndex < candidates.length; currentIndex++){
                current.add(candidates[currentIndex]);
                backtrack(candidates, result, current, k - 1, currentIndex + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    public List<List<Integer>> combineFormula(int n, int k) {
        if(n == k){
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> nn = new ArrayList<>();
            for(int i = 1; i <= k; i++){
                nn.add(i);
            }
            result.add(nn);
            return result;
        }
        else if(k == 1){
            List<List<Integer>> result = new ArrayList<>();
            for(int i = 1; i <= n; i++){
                List<Integer> n1 = new ArrayList<>();
                n1.add(i);
                result.add(n1);
            }
            return result;
        }
        else{
            List<List<Integer>> result = combineFormula(n - 1, k -1);
            for(List<Integer> candidate : result){
                candidate.add(n);
            }
            result.addAll(combineFormula(n - 1, k));
            return result;
        }
    }

    /**
     * Given a digit string, return all possible letter combinations that the number could represent.
     * {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return result;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> candidates = new ArrayList<>();
        for(char c : digits.toCharArray()){
            if(c != '0' && c != '1'){
                candidates.add(mapping[c - '0']);
            }
        }
        backtrack(result, candidates, new StringBuilder(""), 0);
        return result;
    }

    private void backtrack(List<String> result, List<String> candidates, StringBuilder sb, int startIndex) {
        if(startIndex == candidates.size()){
            result.add(sb.toString());
        }
        else{
            for(char c : candidates.get(startIndex).toCharArray()){
                sb.append(c);
                backtrack(result, candidates, sb, startIndex + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
