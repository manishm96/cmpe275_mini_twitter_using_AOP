package edu.sjsu.cmpe275.aop.tweet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import java.util.UUID;

@Aspect
@Order(1)
public class ValidationAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..))")
	public void before_tweet_conditions(JoinPoint joinPoint) {
		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		String message = joinPoint.getArgs()[1].toString();
		
		if(user == null || message == null) {
			
			System.out.println("Throwing an IllegalArgumentException as one or both of the arguments has a null value");
			throw new IllegalArgumentException();
			
		}
		
		if(user == "" || message == "") {
			
			System.out.println("Throwing an IllegalArgumentException as one or both of the arguments are empty ");
			throw new IllegalArgumentException();
			
		}
		
		
		if(message.length() > 140) {
			
			System.out.println("Throwing IllegalArgumentException as the length of the tweet is exceeding 140 characters");
			throw new IllegalArgumentException();
			
		}
		
			
		}
	
	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.reply(..))")
	public void before_reply_conditions(JoinPoint joinPoint) {
		
		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		UUID originalMessage = (UUID)joinPoint.getArgs()[1];
		String message =  joinPoint.getArgs()[2].toString();
		
		if(user == null || message == null || originalMessage == null) {
			
			System.out.println("Throwing IllegalArgumentException as the parameters in reply message is null");
			throw new IllegalArgumentException();
			
		}
		
		if(user == "" || message == "") {
			
			System.out.println("Throwing IllegalArgumentException as the parameters in reply message is empty");
			throw new IllegalArgumentException();
		}
		
		
		
			
		
		
		
	}
	
	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
	public void before_follow_conditions(JoinPoint joinPoint) {
		
		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String follower = joinPoint.getArgs()[0].toString();
		String followee = joinPoint.getArgs()[1].toString();
		
		if(follower == null || followee == null) {
			
			System.out.println("Throwing an IllegalArgumentException as the parameters have null values");
			throw new IllegalArgumentException();
		}
		
		if(follower == "" || followee == "") {
			
			System.out.println("Throwing an IllegalArgumentException as the parameters are empty.");
			throw new IllegalArgumentException();
		}
		
		
		
		
	}
	
	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
	public void before_block_conditions(JoinPoint joinPoint) {
		
		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		String follower = joinPoint.getArgs()[1].toString();
		
		if(user == null || follower == null) {
			
			System.out.println("Throwing an IllegalArgumentException as either of the parameters in block is null");
			throw new IllegalArgumentException();
		}
		
		if(user == "" || follower == "") {
			
			System.out.println("Throwing IllegalArgumentException as either of the parameters in block are empty");
			throw new IllegalArgumentException();
		}
		
	}
	
	@Before("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.like(..))")
	public void before_like_conditions(JoinPoint joinPoint) {
		
		System.out.printf("Permission check before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		UUID messageId = (UUID)joinPoint.getArgs()[1];
		
		if(user == null || messageId == null) {
			
			System.out.println("Throwing IllegalArgumentException as either of the two parameters in the like method is null");
			throw new IllegalArgumentException();
		}
		
		if(user == "") {
			
			System.out.println("Throwing IllegalArgumentException as either of the two parameters in the like method is null");
			throw new IllegalArgumentException();
		}
		
	}
}

