package com.roland.shuzu;


import com.sun.istack.internal.NotNull;

import java.util.*;


class Solution {
    public static String nextClosestTime(String time) {
        char[] array = time.toCharArray();
        array[2] = array[3];
        array[3] = array[4];
        char smallest = array[0];
        for(char c : array){
            if(c < smallest){
                smallest = c;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int index = 3; index >=0; index--){
            int immediateIndex = immediateGreaterIndex(array, index);
            if(immediateIndex != -1){
                array[index] = array[immediateIndex];
                assignValueToSmallest(array, index + 1, 3, smallest);
                return result.append(array[0]).append(array[1]).append(':').append(array[2]).append(array[3]).toString();
            }
        }
        return result.append(smallest).append(smallest).append(':').append(smallest).append(smallest).toString();
    }

    public static void assignValueToSmallest(char[] array, int start, int end, char small){
        for(int i = start; i <= end; i++){
            array[i] = small;
        }
    }

    public static int immediateGreaterIndex(char[] array, int index){
        char immediateGreater = 'A';
        int result = -1;
        for(int i = 3; i >= 0; i--){
            if(i != index && array[i] > array[index] && array[i] < immediateGreater){
                if(isEligibleAssignIToIndex(index,i, array)){
                    immediateGreater = array[i];
                    result = i;
                }
            }
        }
        return result;
    }

    public static boolean isEligibleAssignIToIndex(int index, int i, char[] array){
        if(index == 3){
            return true;
        }
        else if(index == 2){
            return array[i] < '6';
        }
        else if(index == 1){
            return array[0] != '2' || (array[0] == '2' && array[i] < '4');
        }
        // index == 0
        else{
            return array[i] < '3';
        }
    }
    public static final int directions[][] = {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {1,-2}, {-1,2}, {-1,-2}};
    public static final double possibilityMove = 0.125d;
    public double[][][] possibilities;
    public boolean[][][] flag;
    public int step;

    public double knightProbability(int N, int K, int r, int c) {
        possibilities = new double[N][N][K];
        flag = new boolean[N][N][K];
        step = K;
        return knightProbabilityX(N, 0, r, c);
    }

    public double knightProbabilityX(int N, int K, int r, int c) {
        if(r < 0 || r >= N || c < 0 || c >= N ){
            return 0d;
        }
        if(K == step){
            return 1d;
        }
        else{
            if(flag[r][c][K]){
                return possibilities[r][c][K];
            }
            else{
                double result = 0d;
                for(int i = 0; i < 8; i++){
                    result += possibilityMove * knightProbability(N,K + 1,r + directions[i][0],c+directions[i][1]);
                }
                flag[r][c][K] = true;
                possibilities[r][c][K] = result;
                return result;
            }
        }
    }


    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    result++;
                    mark(i,j,grid);
                }
            }
        }
        return result;
    }

    public void mark(int row, int column, int[][] grid){
        grid[row][column] = 0;
        if(row > 0 && grid[row - 1][column] == 1){
            mark(row - 1, column, grid);
        }
        if(row < grid.length - 1 && grid[row + 1][column] == 1){
            mark(row + 1, column, grid);
        }
        if(column > 0 && grid[row][column - 1] == 1){
            mark(row, column - 1, grid);
        }
        if(column < grid[0].length - 1 && grid[row][column + 1] == 1){
            mark(row, column + 1, grid);
        }
    }












    public int numDistinctIslands(int[][] grid) {
        Set<List<Integer>> islands = new HashSet<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    List<Integer> island = new ArrayList<>();
                    mark(i, j, grid, island);
                    if(isDistinctIsland(island, islands)){
                        islands.add(island);
                    }
                }
            }
        }
        return islands.size();
    }

    public void mark(int row, int column, int[][] grid, List<Integer> sb){
        grid[row][column] = 0;
        sb.add(row * grid[0].length + column);
        if(row > 0 && grid[row - 1][column] == 1){
            mark(row - 1, column, grid, sb);
        }
        if(row < grid.length - 1 && grid[row + 1][column] == 1){
            mark(row + 1, column, grid, sb);
        }
        if(column > 0 && grid[row][column - 1] == 1){
            mark(row, column - 1, grid, sb);
        }
        if(column < grid[0].length - 1 && grid[row][column + 1] == 1){
            mark(row, column + 1, grid, sb);
        }
    }

    public boolean isDistinctIsland(List<Integer> island, Set<List<Integer>> islands){
        for(List<Integer> s : islands){
            if(isSameIsland(island, s)){
                return false;
            }
        }
        return true;
    }

    public boolean isSameIsland(List<Integer> island, List<Integer> s){
        if(island.size() == s.size()){
            int offset = island.get(0) - s.get(0);
            for(int k = 1; k < island.size(); k++){
                if(island.get(k) - s.get(k) != offset){
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int frequency = 0;
        for(int i : nums){
            if(map.containsKey(i)){
                int value = map.get(i);
                map.put(i, ++value);
            }
            else{
                map.put(i,1);
            }
            if(map.get(i) > frequency){
                frequency = map.get(i);
            }
        }
        if(frequency == 1){
            return 1;
        }
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            if(map.get(it.next()) != frequency){
                it.remove();
            }
        }
        int shortest = nums.length;
        for(Integer key : map.keySet()){
            int start = -1;
            int end = -1;
            for(int index = 0; index < nums.length && (end - start + 1 < shortest);index++){
                if(nums[index] == key){
                    if(start == -1){
                        start = index;
                    }
                    else{
                        end = index;
                    }
                }
            }
            int currentLength = end - start + 1;
            if(currentLength < shortest){
                shortest = currentLength;
            }
        }
        return shortest;

    }

    public int countBinarySubstrings(@NotNull String s) {


        char[] array = s.toCharArray();
        int result = 0;
        for(int start = 0; start < array.length; start++){
            int[] record = new int[2];
            record[array[start] - 48]++;
            for(int current = start + 1; current < array.length; current++){
                record[array[current] - 48]++;
                if(record[0] == record[1]){
                    result++;
                    break;
                }
                if(record[0] != 0 && record[1] != 0){
                    if(array[current] != array[current - 1]
                            || (record[0] > record[1] && array[current] == 0) || (record[1] > record[0] && array[current] == 1)){
                        break;
                    }
                }
            }
        }
        return result;
    }



    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0){
            return result;
        }
        HashMap<String, Integer> maps = new HashMap<>();
        for(String word: words){
            maps.put(word, maps.getOrDefault(word, 0) + 1);
        }

        List<String>[] bucket = new ArrayList[maps.size() + 1];
        for(Map.Entry<String, Integer> entry : maps.entrySet()){
            int index = entry.getValue();
            if(bucket[index] == null){
                List<String> list = new ArrayList<>();
                list.add(entry.getKey());
                bucket[index] = list;
            }
            else{
                bucket[index].add(entry.getKey());
            }
        }
        for(int i = bucket.length - 1;i > 0; i--){
            if(bucket[i] != null){
                Collections.sort(bucket[i]);
                for(String s : bucket[i]){
                    result.add(s);
                    if(result.size() == k){
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1){
            return 0;
        }
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);
        }
        int result = 0;
        for(int left = 0; left < prefix.length; left++){
            int high = nums.length;
            int low = left + 1;
            Double diff = Double.MAX_VALUE;
            int resultIndex = -1;
            while(low <= high){
                int mid = low + (high - low) / 2;
                double currentDiff = logk - (prefix[mid] - prefix[left]);
                if(currentDiff > 0){
                    if(currentDiff < diff){
                        diff = currentDiff;
                        resultIndex = mid;
                    }
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
            if(resultIndex != -1){
                result += resultIndex - left;
            }
        }
        return result;
    }





    public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        if(n <= 0 || k <= 0 || k > n){
            return result;
        }

        boolean[] records = new boolean[n];
        StringBuilder sb = new StringBuilder("");
        int[] candidates = new int[n];
        for(int i = 0; i < n; i++){
            candidates[i] = i + 1;
        }
        Set<Integer> differences = new HashSet<>();
        backTrack(result, candidates, sb, records, differences, k);
        return result;
    }


    public void backTrack(int[] result, int[] candidates, StringBuilder current, boolean[] records, Set<Integer> differences, int k) {
        if(differences.size() == k && current.length() == candidates.length){
            char[] array = current.toString().toCharArray();
            for(int i = 0; i < candidates.length; i++){
                result[i] = array[i] - '0';
            }
            return;
        }
        else{
            for(int i = 0; i < candidates.length; i++){
                int difference = -1;
                if(current.length() > 0){
                    difference = Math.abs(candidates[i] - (current.charAt(current.length() -1) - '0'));
                }
                if(current.length() == 0
                        || (!records[i] && (differences.size() < k || (differences.size() == k && differences.contains(difference))))) {
                    boolean notContainsBefore = false;
                    if(difference != -1){
                        notContainsBefore = differences.add(difference);
                    }
                    current.append(candidates[i]);
                    records[i] = true;
                    backTrack(result, candidates, current, records, differences, k);
                    if(result[0] != 0){
                        return;
                    }
                    records[i] = false;
                    if(notContainsBefore && difference != -1){
                        differences.remove(difference);
                    }
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }





    private List<String> buildString(char[][] board) {
        StringBuilder sb = new StringBuilder("");
        List<String> result = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                sb.append(board[i][j]);
            }
            result.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return result;
    }

    public class LRUCache {

        public HashMap<Integer, DNode> hashmap = new HashMap<Integer, DNode>();
        private DNode head;
        private DNode end;
        private int capacity;
        private int len;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            len = 0;
        }

        public int get(int key) {
            if (hashmap.containsKey(key)) {
                DNode node = hashmap.get(key);
                removeNode(node);
                setHead(node);
                return node.value;
            } else
                return -1;
        }

        public void setHead(DNode node) {
            node.next = head;
            node.prev = null;
            if (head != null)
                head.prev = node;
            head = node;
            if (end == null)
                end = node;
        }

        public void removeNode(DNode node) {
            DNode current = node;
            DNode prev = node.prev;
            DNode next = node.next;

            if (prev != null)
                prev.next = next;
            else
                head = next;
            if (next != null)
                next.prev = prev;
            else
                end = prev;
        }

        public void put(int key, int value) {
            if (!hashmap.containsKey(key)) {
                DNode newNode = new DNode(key, value);
                if (len < capacity) {
                    len++;
                } else {
                    hashmap.remove(end.key);
                    removeNode(end);
                }
                hashmap.put(key, newNode);
                setHead(newNode);
            } else {
                DNode oldNode = hashmap.get(key);
                removeNode(oldNode);
                oldNode.value = value;
                setHead(oldNode);
            }
        }

        private class DNode {
            private DNode prev;
            private DNode next;
            private int value;
            private int key;

            public DNode(int key, int value) {
                this.value = value;
                this.key = key;
            }
        }
    }
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if(words1 == null || words2 == null || words1.length != words2.length){
            return false;
        }
        Map<String, Set<String>> dictionary = new HashMap<>();
        for(String[] pair : pairs){
            if(dictionary.get(pair[0]) == null){
                Set<String> similar = new HashSet<>();
                similar.add(pair[1]);
                dictionary.put(pair[0],similar);
            }
            else{
                Set<String> set = dictionary.get(pair[0]);
                set.add(pair[1]);
                dictionary.put(pair[0], set);
            }
        }
        for(int i = 0; i < words1.length; i++){
            if(!words1.equals(words2) && (dictionary.get(words1[i]) == null || !dictionary.get(words1[i]).contains(words2[i]))
                    &&
                    (dictionary.get(words2[i]) == null || !dictionary.get(words2[i]).contains(words1[i]))){
                return false;
            }
        }
        return true;

    }
    public static void main(String[] args){



        Solution solution = new Solution();
        String[] words1 = {"one","excellent","meal"};
        String[] words2 = {"one","good","dinner"};
        String[][] pairs = {{"excellent","good"},{"dinner","meal"}};
        Set<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2);
        for(Integer i : set){
            System.out.println(i);
        }
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int targetValue = image[sr][sc];
        boolean[][] isTravel = new boolean[image.length][image[0].length];
        dfs(image, sr, sc, newColor, targetValue, isTravel);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, int targetValue, boolean[][] isTravel) {
        image[sr][sc] = newColor;
        isTravel[sr][sc] = true;
        if(sr > 0 && !isTravel[sr - 1][sc] && image[sr - 1][sc] == targetValue){
            dfs(image, sr - 1, sc, newColor, targetValue, isTravel);
        }
        if(sr < image.length - 1 && !isTravel[sr + 1][sc] && image[sr + 1][sc] == targetValue){
            dfs(image, sr + 1, sc, newColor, targetValue, isTravel);
        }
        if(sc > 0 && !isTravel[sr][sc - 1] && image[sr][sc - 1] == targetValue){
            dfs(image, sr, sc - 1, newColor, targetValue, isTravel);
        }
        if(sc < image[0].length - 1 && !isTravel[sr][sc + 1] && image[sr][sc + 1] == targetValue){
            dfs(image, sr, sc + 1, newColor, targetValue, isTravel);
        }
    }

    public int[] asteroidCollision(int[] asteroids) {
        if(asteroids == null || asteroids.length == 0){
            return new int[0];
        }
        List<Integer> negativeAsteroids = new ArrayList<>();
        boolean seePositive = false;
        for(int i = 0; i < asteroids.length; i++){
            if(!seePositive && asteroids[i] < 0){
                continue;
            }
            if(!seePositive && asteroids[i] > 0){
                seePositive = true;
                continue;
            }
            if(seePositive && asteroids[i] < 0){
                negativeAsteroids.add(i);
            }
        }
        for(int negativeIndex : negativeAsteroids){
            for(int i = negativeIndex - 1; i >= 0; i -- ){
                if(asteroids[i] > 0 && Math.abs(asteroids[negativeIndex]) > asteroids[i]) {
                    asteroids[i] = 0;
                }
                else if(asteroids[i] > 0 && Math.abs(asteroids[negativeIndex]) < asteroids[i]) {
                    asteroids[negativeIndex] = 0;
                    break;
                }
                else if(asteroids[i] > 0 && Math.abs(asteroids[negativeIndex]) == asteroids[i]) {
                    asteroids[i] = 0;
                    asteroids[negativeIndex] = 0;
                    break;
                }
            }
        }
        List<Integer> result1 = new ArrayList<>();
        for(int i : asteroids){
            if(i != 0){
                result1.add(i);
            }
        }
        int[] result = new int[result1.size()];
        for(int i = 0; i < result1.size(); i++){
            result[i] = result1.get(i);
        }
        return result;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjecent = new HashMap<>();

        for(int[] prerequisite : prerequisites){
            if(adjecent.get(prerequisite[1]) == null){
                List<Integer> adjNodes = new ArrayList<>();
                adjNodes.add(prerequisite[0]);
                adjecent.put(prerequisite[1], adjNodes);
            }
            else{
                List<Integer> adjNodes = adjecent.get(prerequisite[1]);
                adjNodes.add(prerequisite[0]);
                adjecent.put(prerequisite[1], adjNodes);
            }
        }
        boolean[] visited = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++){
            if(isCircle(adjecent, visited, i)){
                return false;
            }
        }
        return true;



    }

    private boolean isCircle(Map<Integer, List<Integer>> adjecent, boolean[] visited, int current) {
        if(!visited[current] && adjecent.get(current) != null){
            visited[current] = true;
            for(Integer nextNode : adjecent.get(current)){
                if(isCircle(adjecent, visited, nextNode)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int time = 0;
        for(int i = 0; i < courses.length; i++){
            priorityQueue.add(courses[i][0]);
            time += courses[i][0];
            if(time > courses[i][1]){
                time -= priorityQueue.poll();
            }
        }
        return priorityQueue.size();
    }



}

