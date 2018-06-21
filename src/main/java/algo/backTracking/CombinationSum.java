package algo.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    /**
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set [2, 3, 6, 7] and target 7,
     * A solution set is:
     * [
     * [7],
     * [2, 2, 3]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return result;
        }
        List<Integer> current = new ArrayList<>();
        backTrackingNoDup(result, current, candidates, target, 0);
        return result;
    }

    private void backTrackingNoDup(List<List<Integer>> result,
                                   List<Integer> current,
                                   int[] candidates,
                                   int target,
                                   int startIndex) {
        if(target == 0){
            result.add(new ArrayList<>(current));
        }
        else{
            for(int i = startIndex; i < candidates.length; i++){
                if(target >= candidates[i]){
                    current.add(candidates[i]);
                    backTrackingNoDup(result, current, candidates, target - candidates[i], i);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /**
     * Given a collection of candidate numbers (C) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.
     * Each number in C may only be used once in the combination.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     * A solution set is:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        backTrackingDup(result, list, candidates, target, 0);
        return result;
    }

    private void backTrackingDup(List<List<Integer>> result,
                                 List<Integer> current,
                                 int[] candidates,
                                 int target,
                                 int startIndex) {
        if(target == 0){
            result.add(new ArrayList<>(current));
        }
        else{
            // already sorted, if the current element is greater than target, the subsequent elements are all greater
            for(int i = startIndex; i < candidates.length && target >= candidates[i]; i++){
                //if i is the start index, there is no duplicates because there isn't any element added yet;
                //if i is not the start index, if the previous element is same, it must be used; so we need to skip
                if(i == startIndex || (i > startIndex && candidates[i] != candidates[i - 1])){
                    current.add(candidates[i]);
                    backTrackingDup(result, current, candidates, target - candidates[i], i + 1);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if(k <= 0 || n <= 0){
            return result;
        }
        int[] candidates = new int[9];
        for(int i = 0; i < candidates.length; i++){
            candidates[i] = i + 1;
        }
        List<Integer> list = new ArrayList<>();
        backTrackingN(result, list, candidates, n, k, 0);
        return result;
    }

    private void backTrackingN(List<List<Integer>> result,
                               List<Integer> current,
                               int[] candidates,
                               int target,
                               int k,
                               int startIndex) {
        if(target == 0 && k == 0){
            result.add(new ArrayList<>(current));
        }
        else{
            // already sorted, if the current element is greater than target, the subsequent elements are all greater
            for(int currentIndex = startIndex; currentIndex < candidates.length && k > 0 && target >= candidates[currentIndex]; currentIndex++){
                current.add(candidates[currentIndex]);
                backTrackingN(result, current, candidates, target - candidates[currentIndex], k - 1, currentIndex + 1);
                current.remove(current.size() - 1);
            }
        }
    }
}
