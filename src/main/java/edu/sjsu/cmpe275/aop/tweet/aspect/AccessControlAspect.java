package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.security.AccessControlException;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

@Aspect
@Order(2)
public class AccessControlAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     * @throws Throwable 
     */

	@SuppressWarnings("removal")
	@Around("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.like(..))")
	public int access_like_aspect(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		
		String user = joinPoint.getArgs()[0].toString();
		String messageId1 = joinPoint.getArgs()[1].toString();
		UUID messageId = UUID.fromString(messageId1);
		Integer result = null;
		
		
		try {
			
			if(!TweetStatsServiceImpl.like_map.containsKey(messageId)) {
				
				result = (Integer)joinPoint.proceed();
				throw new AccessControlException("An accessControlException is triggered");
				
			}
			
			
			System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
			throw e;
		}
		return result.intValue();
	}

}
