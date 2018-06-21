package algo.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * For example,
     * If nums = [1,2,3], a solution is:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        List<Integer> emptySet = new ArrayList<>();
        backTrack(result, emptySet, nums, 0);
        return result;
    }

    private void backTrack(List<List<Integer>> result, List<Integer> current, int[] nums, int startIndex) {
        // need to create a new arraylist.
        result.add(new ArrayList<>(current));
        for(int currentIndex = startIndex; currentIndex < nums.length; currentIndex++){
            current.add(nums[currentIndex]);
            backTrack(result, current, nums, currentIndex + 1);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        List<Integer> emptySet = new ArrayList<>();
        backTrackDup(result, nums, emptySet, 0);
        return result;
    }

    private void backTrackDup(List<List<Integer>> result, int[] nums, List<Integer> current, int startIndex) {
        result.add(new ArrayList<>(current));
        for(int currentIndex = startIndex; currentIndex < nums.length; currentIndex++){
            if(currentIndex == startIndex || nums[currentIndex - 1] < nums[currentIndex]){
                current.add(nums[currentIndex]);
                backTrackDup(result, nums, current, currentIndex + 1);
                current.remove(current.size() - 1);
            }
        }
    }

}
