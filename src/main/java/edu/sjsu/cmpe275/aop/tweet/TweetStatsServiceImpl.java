package edu.sjsu.cmpe275.aop.tweet;

import java.util.*;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class TweetStatsServiceImpl implements TweetStatsService {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
	
	public int longestTweetLength;
	public static  int retry_count = 0;
	public static HashMap <UUID,TreeMap<String,String>> tweet_map = new HashMap<UUID,TreeMap<String,String>>();
	public static TreeMap <String,String> store_tweet;
	public static HashMap <UUID,TreeMap<String,String>> reply_map = new HashMap<UUID,TreeMap<String,String>>();
	public static TreeMap <String,String> store_reply;
	public HashMap <String,HashSet<String>> follow_map = new HashMap<String,HashSet<String>>();
	public HashSet<String> followers;
	//private HashMap<String,Integer> follower_count = new HashMap<String,Integer>();
	public HashMap <String,HashSet<String>> block_map = new HashMap<String,HashSet<String>>();
	public HashSet<String> blocked_users;
	public static HashMap<UUID ,HashSet<String>> like_map = new HashMap<UUID , HashSet<String>>();
	public static HashSet<String> likes_from_users;
	TreeMap<String,Integer> followerCountMap = new TreeMap<String,Integer>();
	TreeMap<UUID,Integer> tweetCountMap = new TreeMap<UUID,Integer>();
	TreeMap<UUID,Integer> likeCountMap = new TreeMap<UUID,Integer>();
	TreeMap<String,Integer> unpopCountMap = new TreeMap<String,Integer>();

	@Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
	    
			longestTweetLength = 0;
			tweet_map.clear();
			store_tweet.clear();
			reply_map.clear();
			store_reply.clear();
			follow_map.clear();
			followers.clear();
			follow_map.clear();
			block_map.clear();
			blocked_users.clear();
			like_map.clear();
			likes_from_users.clear();
			followerCountMap.clear();
			tweetCountMap.clear();
			likeCountMap.clear();
			unpopCountMap.clear();
			
			
		
			
		
	}
    
	@Override
	public int getLengthOfLongestTweet() {
		// TODO Auto-generated method stub
		return longestTweetLength;
	}

	@Override
	public String getMostFollowedUser() {
		// TODO Auto-generated method stub
		
		
		for(String followee : follow_map.keySet()) {
		int count = follow_map.get(followee).size();
		followerCountMap.put(followee, count);
		}
		Set<Entry<String, Integer>> followersEntrySet = followerCountMap.entrySet();
		List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(
		   followersEntrySet);
		//Compare and sort based on the value i.e count of followers
		   Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
		    public int compare(Map.Entry<String, Integer> followee1,
		               Map.Entry<String, Integer> followee2) {
		               return followee2.getValue().compareTo(followee1.getValue());
		           }
		       });
		   return sortedList.get(0).getKey();


		}
		        
	

	@Override
	public UUID getMostPopularMessage() {
		// TODO Auto-generated method stub
		
		
		for(UUID messageId : tweet_map.keySet()) {
		int count = tweet_map.get(messageId).size();
		tweetCountMap.put(messageId, count);
		}
		Set<Entry<UUID, Integer>> tweetsEntrySet = tweetCountMap.entrySet();
		List<Entry<UUID, Integer>> sortedList = new ArrayList<Entry<UUID, Integer>>(
		   tweetsEntrySet);
		//Compare and sort based on the value i.e count of followers
		   Collections.sort(sortedList, new Comparator<Map.Entry<UUID, Integer>>() {
		    public int compare(Map.Entry<UUID, Integer> messageId1,
		               Map.Entry<UUID, Integer> messageId2) {
		               return messageId2.getValue().compareTo(messageId1.getValue());
		           }
		       });
		   return sortedList.get(0).getKey();
	}
	
	@Override
	public String getMostProductiveReplier() {
		// TODO Auto-generated method stub
		
		/*TreeMap<String,Integer> replyCountMap = new TreeMap<String,Integer>();
		for(String user : store_reply.keySet()) {
		int count = store_reply.get(user).size();
		replyCountMap.put(user, count);
		}
		Set<Entry<String, Integer>> usersEntrySet = replyCountMap.entrySet();
		List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(
		   usersEntrySet);
		//Compare and sort based on the value i.e count of followers
		   Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
		    public int compare(Map.Entry<String, Integer> user1,
		               Map.Entry<String, Integer> user2) {
		               return user2.getValue().compareTo(user1.getValue());
		           }
		       });
		   return sortedList.get(0).getKey();*/
		
		return null;

	}

	@Override
	public UUID getMostLikedMessage() {
		// TODO Auto-generated method stub
		
		
		for(UUID messageId : like_map.keySet()) {
		int count = like_map.get(messageId).size();
		likeCountMap.put(messageId, count);
		}
		Set<Entry<UUID, Integer>> likesEntrySet = likeCountMap.entrySet();
		List<Entry<UUID, Integer>> sortedList = new ArrayList<Entry<UUID, Integer>>(
		   likesEntrySet);
		//Compare and sort based on the value i.e count of followers
		   Collections.sort(sortedList, new Comparator<Map.Entry<UUID, Integer>>() {
		    public int compare(Map.Entry<UUID, Integer> messageId1,
		               Map.Entry<UUID, Integer> messageId2) {
		               return messageId2.getValue().compareTo(messageId1.getValue());
		           }
		       });
		   return sortedList.get(0).getKey();

		}
		

	

	@Override
	public String getMostUnpopularFollower() {
		// TODO Auto-generated method stub
		
		for(String user : block_map.keySet()) {
		int count = block_map.get(user).size();
		unpopCountMap.put(user, count);
		}
		Set<Entry<String, Integer>> unpopEntrySet = unpopCountMap.entrySet();
		List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(
		   unpopEntrySet);
		//Compare and sort based on the value i.e count of followers
		   Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
		    public int compare(Map.Entry<String, Integer> user1,
		               Map.Entry<String, Integer> user2) {
		               return user2.getValue().compareTo(user1.getValue());
		           }
		       });
		   return sortedList.get(0).getKey();
	}

	@Override
	public UUID getLongestMessageThread() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void add_tweet(String user,String message, UUID return_value) {
		
		//Updating the stats for longestTweetLength()
				if(message.length() > longestTweetLength) {
					longestTweetLength = message.length();
						
				}
				
				store_tweet = tweet_map.get(user);
				
				if(store_tweet == null) {
					
					store_tweet = new TreeMap<String,String>();
				}
				
				store_tweet.put(user, message);
				tweet_map.put(return_value, store_tweet);
	}
	
	
    public void add_reply(String user,UUID originalMessage,String message,UUID return_value) {
    	
    	store_reply = reply_map.get(originalMessage);
		if(store_reply == null) {
			
			store_reply = new TreeMap<String,String>();
		}
		
		store_reply.put(user, message);
		store_tweet.put(user, message);
		tweet_map.put(return_value, store_tweet);
		reply_map.put(originalMessage, store_reply);
		
		
	}
	
	public void add_followers(String follower, String followee) {
		
		followers = follow_map.get(followee);
		
		if (followers == null)
		{
			followers = new HashSet<String>();
		}
		followers.add(follower);
		follow_map.put(followee,followers);
		System.out.println("Follwer added successfully for " + followee);
	}
	
	
	
	public void add_blocked(String user,String follower) {
		
		blocked_users = block_map.get(user);
		
		if (!block_map.containsKey(user))
		{
			blocked_users = new HashSet<String>();
		}
		blocked_users.add(follower);
		block_map.put(user,blocked_users);
		followers = follow_map.get(user);
		
		if(followers != null && followers.contains(follower))
			followers.remove(follower);
		
	}
	
	
	public void add_like(String user,UUID messageId) {
		
		likes_from_users = like_map.get(messageId);
		
		if(!like_map.containsKey(messageId) ) {
			
			likes_from_users = new HashSet<String>();
		}
		
		likes_from_users.add(user);
		like_map.put(messageId, likes_from_users);
		
		System.out.println(user+ "liked the following message:" +messageId);
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	

}



