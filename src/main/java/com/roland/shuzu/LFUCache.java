package com.roland.shuzu;

import com.sun.org.apache.regexp.internal.RE;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.util.*;

class LFUCache {

    private Map<Integer, Integer> data;
    private Map<Integer, LinkedHashSet<Integer>> hitsToKey;
    private Map<Integer, Integer> keyToHits;
    private int capacity;
    private int minimumHits;

    public LFUCache(int capacity) {
        data = new HashMap<>();
        hitsToKey = new HashMap<>();
        keyToHits = new HashMap<>();
        this.capacity = capacity;
        minimumHits = Integer.MAX_VALUE;
    }

    public int get(int key) {
        if (capacity == 0 || data.get(key) == null) {
            return -1;
        } else {
            updateExistKey(key);
            return data.get(key);
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (data.size() == capacity && data.get(key) == null) {
            purge();
        }
        if (data.get(key) == null) {
            updateNotExistKey(key);
        } else {
            updateExistKey(key);
        }
        data.put(key, value);
    }

    private void purge() {

        // find the least recently element with the minimum hits
        Integer element = hitsToKey.get(minimumHits).iterator().next();
        hitsToKey.get(minimumHits).remove(element);
        data.remove(element);
        keyToHits.remove(element);
        if (hitsToKey.get(minimumHits).size() == 0) {
            //find the minimum hits
            int min = Integer.MAX_VALUE;
            for (Integer i : keyToHits.values()) {
                if (min > i) {
                    min = i;
                }
            }
            minimumHits = min;
        }
    }

    private void updateNotExistKey(int key) {
        keyToHits.put(key, 0);
        if (hitsToKey.get(0) == null) {
            LinkedHashSet<Integer> set = new LinkedHashSet<>();
            set.add(key);
            hitsToKey.put(0, set);
        } else {
            hitsToKey.get(0).add(key);
        }
        minimumHits = 0;
    }

    private void updateExistKey(int key) {
        int originalHits = keyToHits.get(key);
        keyToHits.put(key, originalHits + 1);
        if (hitsToKey.get(originalHits + 1) == null) {
            LinkedHashSet<Integer> set = new LinkedHashSet<>();
            set.add(key);
            hitsToKey.put(originalHits + 1, set);
            hitsToKey.get(originalHits).remove(key);
            if (originalHits == minimumHits && hitsToKey.get(originalHits).size() == 0) {
                minimumHits++;
            }
        } else {
            hitsToKey.get(originalHits + 1).add(key);
            hitsToKey.get(originalHits).remove(key);
            if (originalHits == minimumHits && hitsToKey.get(originalHits).size() == 0) {
                minimumHits++;
            }
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        sumNumbers(root, result, sb);
        int sum = 0;
        for (String s : result) {
            sum += Integer.valueOf(s);
        }
        return sum;
    }

    private void sumNumbers(TreeNode node, List<String> list, StringBuilder sb) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            list.add(sb.toString());
            return;
        }
        if (node.left != null) {
            sumNumbers(node.left, list, sb);
        }
        if (node.right != null) {
            sumNumbers(node.right, list, sb);
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == rightHeight) {
            return 1 + 1 << leftHeight - 1 + countNodes(root.right);
        } else {
            return 1 + 1 << rightHeight - 1 + countNodes(root.left);
        }
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + height(root.left);
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return new ArrayList<>();
        }
        List<Integer> current = new ArrayList<>();
        backTrack(result, nums, current, 0);
        return new ArrayList<>(result);
    }

    private void backTrack(Set<List<Integer>> result, int[] nums, List<Integer> current, int start) {
        if (current.size() > 1) {
            result.add(new ArrayList<>(current));
        }
        for (int i = start; i < nums.length; i++) {
            if (current.size() == 0 || current.get(current.size() - 1) <= nums[i]) {
                current.add(nums[i]);
                backTrack(result, nums, current, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
//        int[] nextArray = new int[101];
//        int[] result = new int[temperatures.length];
//        Arrays.fill(nextArray, Integer.MAX_VALUE);
//        nextArray[temperatures[temperatures.length - 1]] = temperatures.length - 1;
//        for(int i = temperatures.length - 2; i >= 0; i--){
//            int temperature = temperatures[i];
//            int warmerIndex = Integer.MAX_VALUE;
//            for(int k = temperature + 1; k <= 100; k++){
//                if(nextArray[k] < warmerIndex){
//                    warmerIndex = nextArray[k] ;
//                }
//            }
//            if(warmerIndex != Integer.MAX_VALUE){
//                result[i] = warmerIndex - i;
//            }
//            nextArray[temperature] = i;
//        }
//        return result;

        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperature) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            } else {
                result[i] = 0;
            }
            stack.push(i);
        }
        return result;
    }

    public int deleteAndEarn(int[] nums) {

        int[] result = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            result[nums[i]]++;
        }
        int previousNumber = -1;
        int usingCurrentProfit = 0;
        int avoidCurrentProfit = 0;
        for (int i = 1; i <= 10000; i++) {
            if (result[i] != 0) {
                int maxPrevious = Math.max(usingCurrentProfit, avoidCurrentProfit);
                if (previousNumber != i - 1) {
                    usingCurrentProfit = maxPrevious + i * result[i];
                    avoidCurrentProfit = maxPrevious;
                } else {
                    usingCurrentProfit = i * result[i] + avoidCurrentProfit;
                    avoidCurrentProfit = maxPrevious;
                }
                previousNumber = i;
            }
        }
        return Math.max(usingCurrentProfit, avoidCurrentProfit);
//        Map<Integer, Integer> numToOccurences = new HashMap<>();
//        for(int i : nums){
//            if(numToOccurences.containsKey(i)){
//                numToOccurences.put(i, numToOccurences.get(i) + 1);
//            }
//            else{
//                numToOccurences.put(i, 1);
//            }
//        }
//        int result = 0;
//        while(!numToOccurences.isEmpty()){
//            int maxgain = Integer.MIN_VALUE;
//            int maxGainNum = -1;
//            for(Map.Entry<Integer, Integer> entry : numToOccurences.entrySet()){
//                int currentLoss = 0;
//                if(numToOccurences.containsKey(entry.getKey() + 1)){
//                    currentLoss += numToOccurences.get(entry.getKey() + 1) * (entry.getKey() + 1);
//                }
//                if(numToOccurences.containsKey(entry.getKey() - 1)){
//                    currentLoss += numToOccurences.get(entry.getKey() - 1) * (entry.getKey() - 1);
//                }
//                int currentgain = numToOccurences.get(entry.getKey()) * entry.getKey();
//                if(currentgain - currentLoss > maxgain){
//                    maxgain = currentgain - currentLoss;
//                    maxGainNum = entry.getKey();
//                }
//            }
//            result += numToOccurences.get(maxGainNum) * maxGainNum;
//            if(numToOccurences.containsKey(maxGainNum + 1)){
//                numToOccurences.remove(maxGainNum + 1);
//            }
//            if(numToOccurences.containsKey(maxGainNum - 1)){
//                numToOccurences.remove(maxGainNum - 1);
//            }
//            numToOccurences.remove(maxGainNum);
//        }
//        return result;
    }


    private boolean isVaild(int[][] grid, int i, int j) {
        if ((j == grid.length - 1 || grid[i][j + 1] == -1) && (i == grid.length - 1 || grid[i + 1][j] == -1)) {
            return false;
        }
        if ((j == 0 || grid[i][j - 1] == -1) && (i == 0 || grid[i - 1][j] == -1)) {
            return false;
        }
        return true;
    }

    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        Stack<Integer> maxStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        int i = 1;
        minStack.push(0);
        while (maxStack.isEmpty() && i < nums.length) {
            if (nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
                minStack.push(i++);
            } else {
                maxStack.push(i++);
            }
        }
        if (maxStack.isEmpty()) {
            return false;
        }
        for (; i < nums.length; i++) {
            if (nums[i] == nums[maxStack.peek()]) {
                int currentOnMax = maxStack.pop();
                if (nums[minStack.peek()] > nums[currentOnMax]) {
                    minStack.push(currentOnMax);
                }
                maxStack.push(i);
                continue;
            }
            if (nums[i] < nums[minStack.peek()]) {
                minStack.push(i);
                continue;
            } else if (nums[i] == nums[minStack.peek()]) {
                continue;
            } else {
                Stack<Integer> stack1 = new Stack<>();
                while (!maxStack.isEmpty()) {
                    Stack<Integer> stack = new Stack<>();
                    while (!minStack.isEmpty() && (minStack.peek() > maxStack.peek() || nums[minStack.peek()] >= nums[i])) {
                        stack.push(minStack.pop());
                    }
                    if (!minStack.isEmpty() && nums[i] < nums[maxStack.peek()]) {
                        return true;
                    } else {
                        while (!stack.isEmpty()) {
                            minStack.push(stack.pop());
                        }
                    }
                    stack1.push(maxStack.pop());
                }
                while (!stack1.isEmpty()) {
                    maxStack.push(stack1.pop());
                }
                maxStack.push(i);
            }
        }
        return false;
    }


//    public static void main(String[] args) {
//        LFUCache lfuCache = new LFUCache(1);
//        TreeNode ten = new TreeNode(10);
//        TreeNode seven = new TreeNode(7);
//        TreeNode eight = new TreeNode(8);
//        TreeNode nine = new TreeNode(9);
//        TreeNode six = new TreeNode(6);
//        TreeNode five = new TreeNode(5);
//        TreeNode four = new TreeNode(4);
//        TreeNode three = new TreeNode(3);
//        TreeNode two = new TreeNode(2);
//        TreeNode one = new TreeNode(1);
//        seven.left = ten;
//        five.right = seven;
//        three.right = five;
//        six.left = eight;
//        six.right = nine;
//        four.left = six;
//        three.left = four;
//        one.left = two;
//        one.right = three;
//        System.out.println(lfuCache.findClosestLeaf(one, 7));
//    }


    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (letters[mid] <= target) {
                start = mid + 1;
            } else if (letters[mid] > target) {
                end = mid;
            }
        }
        if (start == letters.length - 1 && letters[start] <= target) {
            return letters[0];
        } else {
            return letters[start];
        }
    }

    private class Result {

        private Result(int distance1, boolean b) {
            distance = distance1;
            isValueFound = b;
        }

        public int distance = 0;
        boolean isValueFound = false;
    }

    public int findClosestLeaf(TreeNode root, int k) {
        return findClosestLeaf(root, k, new Result(0, false));
    }

    public int findClosestLeaf(TreeNode root, int k, Result result) {
        if (root.left == null && root.right == null && root.val == k) {
            result.distance = 0;
            result.isValueFound = true;
            return root.val;
        } else if (root.left == null && root.right == null && root.val != k) {
            return root.val;
        } else {
            int leftNode = -1;
            int rightNode = -1;
            Result leftResult = null;
            Result rightResult = null;
            if (root.val == k) {
                if (root.left != null) {
                    leftResult = new Result(1, true);
                    leftNode = findClosestLeaf(root.left, k, leftResult);
                }
                if (root.right != null) {
                    rightResult = new Result(1, true);
                    rightNode = findClosestLeaf(root.right, k, rightResult);
                }
                if (leftResult == null) {
                    result.distance = rightResult.distance;
                    result.isValueFound = true;
                    return rightNode;
                } else if (rightResult == null) {
                    result.distance = leftResult.distance;
                    result.isValueFound = true;
                    return leftNode;
                } else {
                    result.isValueFound = true;
                    if (leftResult.distance < rightResult.distance) {
                        result.distance = leftResult.distance;
                        return leftNode;
                    } else {
                        result.distance = rightResult.distance;
                        return rightNode;
                    }
                }
            } else {
                if (root.left != null) {
                    leftResult = new Result(result.distance + 1, result.isValueFound);
                    leftNode = findClosestLeaf(root.left, k, leftResult);
                }
                if (root.right != null) {
                    rightResult = new Result(result.distance + 1, result.isValueFound);
                    rightNode = findClosestLeaf(root.right, k, rightResult);
                }
                if ((root.left != null && leftResult.isValueFound) || (root.right != null && rightResult.isValueFound)) {
                    result.isValueFound = true;
                }
                if (root.left != null && root.right != null) {
                    if ((leftResult.isValueFound && rightResult.isValueFound) || (!leftResult.isValueFound && !rightResult.isValueFound)) {
                        if (leftResult.distance < rightResult.distance) {
                            result.distance = leftResult.distance;
                            return leftNode;
                        } else {
                            result.distance = rightResult.distance;
                            return rightNode;
                        }
                    } else {

                    }
                } else if (root.left != null) {
                    result.distance = leftResult.distance;
                    return leftNode;
                } else {
                    result.distance = rightResult.distance;
                    return rightNode;
                }
            }
        }
        return 0;
    }


    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) {
            return cost[0] < cost[1] ? cost[0] : cost[1];
        }
        int prepre = 0;
        int pre = cost[0];

        for (int i = 2; i <= cost.length; i++) {
            int temp = pre;
            pre = Math.min(prepre + cost[i - 2], pre + cost[i - 1]);
            prepre = temp;
        }
        int startFrom0 = pre;

        prepre = 0;
        pre = cost[1];
        for (int i = 3; i <= cost.length; i++) {
            int temp = pre;
            pre = Math.min(prepre + cost[i - 2], pre + cost[i - 1]);
            prepre = temp;
        }
        int startFrom1 = pre;

        return startFrom0 < startFrom1 ? startFrom0 : startFrom1;
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        char[] array = licensePlate.toLowerCase().toCharArray();
        int[] record = new int[26];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 'a' && array[i] <= 'z') {
                record[array[i] - 'a']++;
                count++;
            }
        }
        String result = "";
        for (String s : words) {
            char[] array1 = s.toCharArray();
            int[] records = Arrays.copyOf(record, record.length);
            int counts = count;
            for (char c : array1) {
                int index = c - 'a';
                if (records[index] != 0) {
                    records[index]--;
                    counts--;
                }
            }
            if (counts == 0 && (result.length() > s.length() || result.equals(""))) {
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] array = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        networkDelayTime(array, 4, 2);
    }

    public  int countCornerRectangles(int[][] grid) {
        int result = 0;
        for (int firstColumn = 0; firstColumn < grid[0].length; firstColumn++) {
            for (int secondColumn = firstColumn + 1; secondColumn < grid[0].length; secondColumn++) {
                int countRows = 0;
                for (int row = 0; row < grid.length; row++) {
                    if (grid[row][firstColumn] == 1 && grid[row][secondColumn] == 1) {
                        countRows++;
                    }
                }
                if (countRows > 1) {
                    result += countRows * (countRows - 1) / 2;
                }
            }
        }
        return result;
    }

    public void ioOperation(boolean isResourceAvailable) {
        if (!isResourceAvailable) {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void futureMethod() {
        throw new NullPointerException("This method is not yet implemented");
    }

    private static class Vertex {
        int vertex;
        int distance;

        public Vertex(int vertex1, int distance1) {
            vertex = vertex1;
            distance = distance1;
        }
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        int[][] distances = new int[N + 1][N + 1];
        for(int[] distance : distances){
            Arrays.fill(distance, -1);
        }
        for (int[] edge : times) {
            distances[edge[0]][edge[1]] = edge[2];
        }
        int[] distance = new int[N + 1];
        Arrays.fill(distance, 10000);
        distance[K] = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.distance - o2.distance;
            }
        });
        for (int i = 1; i <= N; i++) {
            Vertex vertex = new Vertex(i, distance[i]);
            queue.offer(vertex);
        }
        while (!queue.isEmpty()) {
            Vertex currentMin = queue.poll();
            while(queue.peek().vertex == currentMin.vertex){
                queue.poll();
            }
            List<Vertex> candidates = new ArrayList<>(queue);
            for (Vertex nextNeighbour : candidates) {
                // the vertex in the queue is a neighbor of currentMin
                if(distances[currentMin.vertex][nextNeighbour.vertex] != -1) {
                    int candiateRouteTime = currentMin.distance + distances[currentMin.vertex][nextNeighbour.vertex];
                    if (candiateRouteTime < distance[nextNeighbour.vertex]) {
                        distance[nextNeighbour.vertex] = candiateRouteTime;
                        queue.offer(new Vertex(nextNeighbour.vertex, candiateRouteTime));
                    }
                }
            }
        }
        int farthest = Integer.MIN_VALUE;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == 10000) {
                return -1;
            } else if (farthest < distance[i]) {
                farthest = distance[i];
            }
        }
        return farthest;

    }

    public int dominantIndex(int[] nums) {
        int max = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[max] < nums[i]){
                max = i;
            }
        }
        int second = -1;
        for(int i = 0; i < nums.length; i++){
            if(i != max && second < nums[i]){
                second = nums[i];
            }
        }
        if(second == 0){
            return max;
        }
        else if(nums[max] / second >= 2){
            return max;
        }
        else{
            return -1;
        }
    }

}