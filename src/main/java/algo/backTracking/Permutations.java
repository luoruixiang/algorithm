package algo.backTracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    /**
     * Given a collection of distinct numbers, return all possible permutations.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        List<Integer> empty = new ArrayList<>();
        boolean[] records = new boolean[nums.length];
        backTrack(nums, result, empty, records);
        return result;
    }

    private void backTrack(int[] nums, List<List<Integer>> result, List<Integer> current, boolean[] records) {
        if(current.size() == nums.length){
            result.add(new ArrayList<>(current));
        }
        else{
            for(int i = 0; i < nums.length; i++){
                if(!records[i]){
                    current.add(nums[i]);
                    records[i] = true;
                    backTrack(nums, result, current, records);
                    records[i] = false;
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        List<Integer> current = new ArrayList<>();
        boolean[] records= new boolean[nums.length];
        backTrackDup(result, nums, current, records);
        return result;
    }

    private void backTrackDup(List<List<Integer>> result, int[] nums, List<Integer> current, boolean[] records) {
        if(current.size() == nums.length){
            result.add(new ArrayList<>(current));
        }
        else{
            for(int i = 0; i < nums.length; i++){
                if(!records[i] && (i == 0 || nums[i - 1] < nums[i])){
                    current.add(nums[i]);
                    records[i] = true;
                    backTrackDup(result, nums, current, records);
                    current.remove(current.size() - 1);
                    records[i] = false;
                }
            }
        }
    }

    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1){
            return;
        }
        int ascendingPosition = -1;
        for(int i = nums.length - 1; i > 0; i--){
            if(nums[i] > nums[i - 1]){
                ascendingPosition = i - 1;
                break;
            }
        }
        if(ascendingPosition == -1){
            Arrays.sort(nums);
            return;
        }
        int largeIndex = nums.length - 1;
        while(nums[largeIndex] <= nums[ascendingPosition]){
            largeIndex--;
        }
        int temp = nums[largeIndex];
        nums[largeIndex] = nums[ascendingPosition];
        nums[ascendingPosition] = temp;
        Arrays.sort(nums, ascendingPosition + 1, nums.length);
    }

    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for(int i = 1; i < factorial.length; i++){
            factorial[i] = factorial[i - 1] * i;
        }
        List<Integer> candidates = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            candidates.add(i);
        }
        StringBuilder sb = new StringBuilder("");
        dfs(factorial, candidates, sb, k - 1, n - 1);
        return sb.toString();
    }

    private void dfs(int[] factorial, List<Integer> candidates, StringBuilder sb, int leftK, int factorIndex) {
        if(factorIndex < 0){
            return;
        }
        else{
            int nextNumber = leftK / factorial[factorIndex];
            sb.append(candidates.get(nextNumber));
            candidates.remove(nextNumber);
            dfs(factorial, candidates, sb, leftK % factorial[factorIndex], factorIndex - 1);
        }
    }

    /**
     * Input:s1 = "ab" s2 = "eidbaooo"
     * Output:True
     * Explanation: s2 contains one permutation of s1 ("ba").
     *
     * s1's characters are all in a substring of s2 and all the characters of that substring are all in s1
     * For sliding window we don't need to recalculate the map every time we step forward. we only need to update
     * the counts of first char of the last window and the last char of the current window.
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() == 0){
            return true;
        }
        if(s1.length() > s2.length()){
            return false;
        }
        char[] array1 = s1.toCharArray();
        int[] records = new int[26];
        for(char c : array1){
            records[c - 'a']++;
        }
        char[] array2 = s2.toCharArray();
        int[] subStringMap = new int[26];
        for(int i = 0; i < array1.length; i++){
            subStringMap[array2[i] - 'a']++;
        }
        for(int slidingwindowstart = 0; slidingwindowstart <= array2.length - array1.length; slidingwindowstart++){
            if(slidingwindowstart > 0){
                subStringMap[array2[slidingwindowstart - 1] - 'a']--;
                subStringMap[array2[slidingwindowstart + array1.length - 1] - 'a']++;
            }
            if(isInclusion(subStringMap, records)){
                return true;
            }
        }
        return false;
    }

    private boolean isInclusion(int[] subStringMap, int[] records) {
        for(int j = 0; j < subStringMap.length; j++){
            if(subStringMap[j] != records[j]){
                return false;
            }
        }
        return true;
    }
}
