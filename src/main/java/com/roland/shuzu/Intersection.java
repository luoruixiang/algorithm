package com.roland.shuzu;

import javafx.util.Pair;

import java.util.*;

public class Intersection {

    Set<int[]> set = new HashSet<>();

    private static final int R = 26;
    private static class Node{
        private Character val;
        private Node[] next = new Node[R];
    }

    public <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }


     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public static int findNumberOfLIS(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] result = new int[nums.length];
        int[] count = new int[nums.length];
        result[0] = 1;
        count[0] = 1;
        int maxNumber = 1;
        int returnVal = 0;
        for(int i = 1; i < nums.length; i++){
            result[i] = 1;
            count[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(result[j] + 1 == result[i]){
                        count[i] += count[j];
                    }
                    else if(result[j] + 1 > result[i]){
                        result[i] = result[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if(maxNumber == result[i]){
                returnVal += count[i];
            }
            if(maxNumber < result[i]){
                maxNumber = result[i];
                returnVal = count[i];
            }
        }
        return returnVal;

    }

    public static int noDuplicateSubstring(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        int length = s.length();
        int max = 0;
        Map<Character, Integer> characters = new HashMap<Character, Integer>();
        int leftIndex = 0;
        int rightIndex = 1;
        characters.put(s.charAt(leftIndex), 0);
        while(rightIndex < length){
            while(rightIndex < length && !characters.containsKey(s.charAt(rightIndex))){
                characters.put(s.charAt(rightIndex), rightIndex);
                rightIndex++;
            }
            max = Math.max(max, rightIndex - leftIndex);
            if(rightIndex < length && characters.containsKey(s.charAt(rightIndex))){
                int nextleftIndex = characters.get(s.charAt(rightIndex)) + 1;
                characters.remove(s.charAt(leftIndex));
                leftIndex = nextleftIndex;
            }
        }
        return max;
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0){
            return result;
        }
        List<String> current = new ArrayList<>();
        Map<String, Boolean> palindromeMap = new HashMap<>();
        backTrack(s, result, current, 0, palindromeMap);
        return result;
    }

    private static void backTrack(String s,
                                  List<List<String>> result,
                                  List<String> current,
                                  int startIndex,
                                  Map<String, Boolean> map){
        if(startIndex == s.length()){
            result.add(new ArrayList<>(current));
        }
        else{
            for(int i = startIndex; i < s.length(); i++){
                if(isPalindrome(s, startIndex, i, map)) {
                    current.add(s.substring(startIndex, i + 1));
                    backTrack(s, result, current, i + 1, map);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String s, int startIndex, int endIndex, Map<String, Boolean> map){
        if(map.get(s) != null && !map.get(s)){
            return false;
        }
        else if(map.get(s) != null && map.get(s)){
            return true;
        }
        else{
            char[] array = s.toCharArray();
            while(startIndex < endIndex){
                if(array[startIndex++] != array[endIndex--]){
                    map.put(s,false);
                    return false;
                }
            }
            map.put(s,true);
            return true;
        }
    }

    public static void main(String[] args){
        System.out.println(partition("aab"));
    }



}

