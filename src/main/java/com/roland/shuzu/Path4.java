package com.roland.shuzu;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Path4 {
    static int count = 0;

    public static void main(String[] args){
        int[] a = {113,229,349,470,485};
        pathSum(a);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    }

    public static int pathSum(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            int index = i / 10;
            int value = i % 10;
            map.put(index, value);
        }
        getPathSum(11, map);
        map.entrySet().iterator();
        return count;
    }

    public static void getPathSum(int index, Map<Integer, Integer> map){


        if(map.get(index) == null){
            return;
        }
        else{
            int leftIndex = (index/10 + 1) * 10 + 2 * (index % 10) - 1;
            int rightIndex = (index/10 + 1) * 10 + 2 * (index % 10);
            if(map.get(leftIndex) != null && map.get(rightIndex) != null){
                count = count + map.get(index) * 2;
            }
            else{
                count += map.get(index);
            }
            getPathSum(leftIndex, map);
            getPathSum(rightIndex, map);
        }
    }
}
