package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

import org.aspectj.lang.annotation.Around;

@Aspect
@Order(0)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     * @throws Throwable 
     */

	
	
	@Around("execution(public int edu.sjsu.cmpe275.aop.tweet.TweetService.*(..))")
	public int Retry(ProceedingJoinPoint joinPoint) throws IOException {
		
		System.out.printf("The following is the execution of aspect %s\n", joinPoint.getSignature().getName());
		Integer result;
		
		
		try {
			
			TweetStatsServiceImpl.retry_count=TweetStatsServiceImpl.retry_count+1;
			result =  (Integer) joinPoint.proceed();

		}
		catch(Throwable e){
			try{
				result =  (Integer) joinPoint.proceed();
			}
			catch(Throwable e1){
				try{
					result =  (Integer) joinPoint.proceed();
				}
				catch(Throwable e2){
					try{
						result =  (Integer) joinPoint.proceed();
					}
					catch(Throwable e3){
						throw new IOException("Network Failure!!!");
					}
				}
			}
		}
		
		return TweetStatsServiceImpl.retry_count;
	}
}
