package com.roland.shuzu;

import java.util.*;

class Twitter {

    private final Map<Integer, Set<Integer>> followMap;
    private final Map<Integer, Set<Tweet>> tweetMap;
    private Long timeStamp = 0L;

    private class Tweet {

        private Integer tweetId;
        private Long time;

        public Tweet(Integer tweetId, Long time){
            this.tweetId = tweetId;
            this.time = time;
        }

        public Integer getTweetId() {
            return tweetId;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }


    /** Initialize your data structure here. */
    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, timeStamp++);
        if(!tweetMap.containsKey(userId)){
            Set<Tweet> tweets = new HashSet<>();
            tweets.add(newTweet);
            tweetMap.put(userId, tweets);
        }
        else{
            tweetMap.get(userId).add(newTweet);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> allTweets = new ArrayList<>();
        if(tweetMap.get(userId) != null){
            for(Tweet tweet : tweetMap.get(userId)){
                allTweets.add(tweet);
            }
        }
        if(followMap.get(userId) != null){
            for(Integer i : followMap.get(userId)){
                if(tweetMap.get(i) != null){
                    for(Tweet tweet1 : tweetMap.get(i)){
                        allTweets.add(tweet1);
                    }
                }
            }
        }
        allTweets.sort(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < 10 && i < allTweets.size(); i++){
            result.add(allTweets.get(i).getTweetId());
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId != followerId){
            Set<Integer> followees;
            if(!followMap.containsKey(followerId)){
                followees = new HashSet<>();
            }
            else{
                followees = followMap.get(followerId);
            }
            followees.add(followeeId);
            followMap.put(followerId, followees);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followMap.containsKey(followerId) && followMap.get(followerId).contains(followeeId)){
            Set<Integer> followees = followMap.get(followerId);
            Iterator<Integer> it = followees.iterator();
            while(it.hasNext()){
                if(it.next().equals(followeeId)){
                    it.remove();
                    return;
                }
            }
        }
    }

    public static void main(String[] args){
        Twitter twitter = new Twitter();
        twitter.postTweet(1,5);
        twitter.postTweet(1,3);
        System.out.println(twitter.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
