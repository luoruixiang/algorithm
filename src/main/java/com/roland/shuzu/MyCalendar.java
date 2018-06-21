package com.roland.shuzu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MyCalendarTwo {

    Set<Event> set = new HashSet<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        Event newEvent = new Event(start, end);
        for(Event e : set){
            if((end > e.start && end <= e.end)
                    || (start >= e.start && start < e.end)
                    || (start <= e.start && end >= e.end)){
                int overlapStart = start > e.start ? start : e.start;
                int overlapEnd = end < e.end ? end : e.end;
                for(Event e1 : set){
                    if(!e1.equals(e)){
                        if((overlapEnd > e1.start && overlapEnd <= e1.end)
                                || (overlapStart >= e1.start && overlapStart < e1.end)
                                || (overlapStart <= e1.start && overlapEnd >= e1.end)){
                            return false;
                        }
                    }
                }
            }
        }
        set.add(newEvent);
        return true;
    }

    private class Event{
        private int start;
        private int end;

        private Event(int a, int b){
            start = a;
            end = b;
        }
    }

    public class MyCalendar {

        Set<Event> set = new HashSet<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            Event newEvent = new Event(start, end);
            for(Event e : set){
                if((end > e.start && end <= e.end)
                        || (start >= e.start && start < e.end)
                        || (start <= e.start && end >= e.end))
                    return false;
            }
            set.add(newEvent);
            return true;
        }

        private class Event{
            private int start;
            private int end;

            private Event(int a, int b){
                start = a;
                end = b;
            }
        }
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int i = left; i <= right; i++){
            int a = i;
            while(a > 0){
                int lastdigit = a % 10;
                if(lastdigit == 0 || i % lastdigit != 0){
                    break;
                }
                else{
                    a = a / 10;
                }
            }
            if(a == 0){
                result.add(i);
            }
        }
        return result;
    }

    private class Trie{

        TrieNode root;

        private Trie(){
            root = new TrieNode('R');
        }

        private void insert(String s){
            char[] array = s.toCharArray();
            TrieNode current = root;
            for(char c : array){
                TrieNode[] next = current.children;
                int index = c - 97;
                if(next[index] == null){
                    next[index] = new TrieNode(c);
                }
                current = next[index];
            }
            current.isEnd = true;
        }

        private boolean startWith(String prefix){
            char[] array = prefix.toCharArray();
            TrieNode current = root;
            for(char c : array){
                TrieNode[] next = current.children;
                if(next[c - 97] == null){
                    return false;
                }
                else{
                    current = next[c - 97];
                }
            }
            return true;
        }

        private class TrieNode{

            char value;
            boolean isEnd;
            TrieNode[] children;

            private TrieNode(char val){
                value = val;
                children = new TrieNode[26];
            }
        }
    }

    public String getPermutation(int n, int k) {
        int[] factors = new int[n];
        factors[1] = 1;
        for(int i = 2; i < n; i++){
            factors[i] = factors[i - 1] * i;
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = n - 1; i > 0; i--){
            int current = k / factors[i];
            int next = k / factors[i];
            if(next == 0){
                sb.append(current);
                for(int j = i + 1; j > 0;j--){
                    sb.append(j);
                }
                break;
            }
            else{
                sb.append(current + 1);
                k = next;
            }
        }
        return sb.toString();
    }

    private void backTrack(String s,
                           List<String> result,
                           StringBuilder candidate,
                           int numberPointCuts,
                           int lastIndex) {
        if(numberPointCuts == 0){
            String lastByte = s.substring(lastIndex);
            if(isValidIPSegment(lastByte)){
                result.add(candidate.toString() + lastByte);
            }
            else{
                return;
            }
        }
        else{
            for(int i = lastIndex; i < s.length() - 1; i++){
                String substring = s.substring(lastIndex, i + 1);
                if(isValidIPSegment(substring)){
                    candidate.append(substring);
                    candidate.append('.');
                    backTrack(s, result, candidate, numberPointCuts - 1, i + 1);
                    candidate.delete(candidate.length() - substring.length() - 1, candidate.length());
                }
                else{
                    break;
                }
            }
        }
    }

    private boolean isValidIPSegment(String lastByte) {
        return lastByte.length() <= 3 && Integer.valueOf(lastByte) <= 255 && (lastByte.length() == 1 || lastByte.charAt(0) != '0');
    }



    private void backTrack(int result, int candidates, StringBuilder sb, int numberPointCuts, int lastIndex) {
    }
}
