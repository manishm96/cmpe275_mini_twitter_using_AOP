package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.util.HashMap;

import java.util.TreeMap;
import java.util.UUID;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

@Aspect
@Order(3)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Autowired TweetStatsServiceImpl stats;
	
	
	@AfterReturning(pointcut="execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..)) && args(user,message)",returning="return_value")
	public void afterret_tweeting(JoinPoint joinPoint, String user, String message, UUID return_value) {
		
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		
		stats.add_tweet(user, message, return_value);
	}
	
	
	
	
	@AfterReturning(pointcut="execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.reply(..)) && args(user,originalMessage,message)",returning="return_value")
	public void afterret_reply(JoinPoint joinPoint, String user,UUID originalMessage,String message, UUID return_value) {
		
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		
		stats.add_reply(user, originalMessage, message, return_value);
	}
	
	
	
	
	@AfterReturning("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
	public void afterret_follow(JoinPoint joinPoint) {
		
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String follower = joinPoint.getArgs()[0].toString();
		String followee = joinPoint.getArgs()[1].toString();
		
		stats.add_followers(follower,followee);
	}
	
	
	@AfterReturning("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
	public void afterret_block(JoinPoint joinPoint) {
		
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		String follower = joinPoint.getArgs()[1].toString();
		
		
		stats.add_blocked(user, follower);
	}
	
	
	@AfterReturning("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.like(..))")
	public void afterret_like(JoinPoint joinPoint) {
		
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		String messageId1 = joinPoint.getArgs()[1].toString();
		UUID messageId = UUID.fromString(messageId1);
		
		
		stats.add_like(user, messageId);
		
	}
	
	
	
	
	
}


