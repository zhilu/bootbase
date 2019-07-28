package org.aop.api;

import org.aop.api.advice.SipmleThrowAdvice;
import org.aop.api.advice.AroundInterceptor;
import org.aop.api.advice.SimpleAfterReturningAdvice;
import org.aop.api.advice.SimpleBeforeAdvice;
import org.aop.api.bean.LoadWorker;
import org.aop.api.bean.Worker;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import junit.framework.TestCase;


public class ApiAdviceTest extends TestCase {
	
	@Test
	public void testMethodBeforeAdvice() {
		Worker woker = new Worker();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SimpleBeforeAdvice());
		pf.setTarget(woker);
		Worker proxy = (Worker) pf.getProxy();
		proxy.work();
		System.out.println(proxy.toString());
		
	}
	
	@Test
	public void testAfterReturningAdvice() {
		Worker woker = new Worker();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SimpleAfterReturningAdvice());
		pf.setTarget(woker);
		Worker proxy = (Worker) pf.getProxy();
		proxy.work();
	}
	
	@Test
	public void testAroundAdvice() {
		LoadWorker woker = new LoadWorker();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new AroundInterceptor());
		pf.setTarget(woker);
		LoadWorker proxy = (LoadWorker) pf.getProxy();
		proxy.work();
		
	}
	
	@Test
	public void testThrowsAdvice() {
		Worker woker = new Worker();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SipmleThrowAdvice());
		pf.setTarget(woker);
		Worker proxy = (Worker) pf.getProxy();
		try {
			proxy.workThrowAny();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			proxy.workThrowSpecific();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
