package org.aop.api.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class AroundInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(invocation.getMethod().getName());
		Object returnValue = invocation.proceed();
		sw.stop();
		System.out.println("Around: " + sw.getLastTaskName() +", Took: "+ sw.getTotalTimeMillis() +" ms");
		return returnValue;
	}

}
