package org.aop.api;

import org.aop.api.advice.SimpleBeforeAdvice;
import org.aop.api.advisor.AdviceRequired;
import org.aop.api.advisor.SimpleAdvice;
import org.aop.api.advisor.SimpleDynamicPointcut;
import org.aop.api.advisor.SimpleStaticPointcut;
import org.aop.api.bean.FlowWorker;
import org.aop.api.bean.IWorker;
import org.aop.api.bean.LoadWorker;
import org.aop.api.bean.Worker;
import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class ApiAdvisorTest {

	@Test
	public void testSimpleStaticPointcut() {
		IWorker worker1 = new Worker(); 
		IWorker worker2 = new LoadWorker(); 
		
		Pointcut pc = new SimpleStaticPointcut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker1);
		IWorker proxy= (IWorker)pf.getProxy();
		proxy.work();
		
		pf.setTarget(worker2);
		proxy= (IWorker)pf.getProxy();
		proxy.work();
     
	}
	
	@Test
	public void testSimpleDynamicPointcut() {
		Worker worker = new Worker(); 
		
		Pointcut pc = new SimpleDynamicPointcut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		System.out.println("-------------------");
		proxy.workHour(1);
		proxy.workHour(100);
		
		proxy.work();
		proxy.work();
		
	}
	
	
	@Test
	public void testNameMatchMethodPointcut() {
		Worker worker = new Worker(); 
		
		NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
		pc.addMethodName("workHour");
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.workHour(1);
		proxy.workHour(2,20.00);
		
		proxy.work();
		
	}
	
	
	@Test
	public void testJdkRegexpMethodPointcut() {
		Worker worker = new Worker(); 
		
		JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
		pc.setPattern(".*work.*");
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.workHour(1);
		proxy.workHour(2,20.00);
		proxy.work();
		System.out.println(proxy.toString());
		
	}
	
	@Test
	public void testAspectJExpressionPointcut() {
		Worker worker = new Worker(); 
		
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		pc.setExpression("execution(* work*(..))");
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.workHour(1);
		proxy.workHour(2,20.00);
		proxy.work();
		System.out.println(proxy.toString());
		
	}
	
	@Test
	public void testAnnotationMatchingPointcut() {
		Worker worker = new Worker(); 
		
		AnnotationMatchingPointcut pc = AnnotationMatchingPointcut
				.forMethodAnnotation(AdviceRequired.class);
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.workHour(1);
		proxy.workHour(2,20.00);
		
	}
	
	@Test
	public void testControlFlowPointcut() {
		Worker worker = new Worker(); 
		Pointcut pc = new ControlFlowPointcut(FlowWorker.class,
				"work");
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.work();
		
		FlowWorker flowWorker = new FlowWorker(proxy);
		flowWorker.work();
		
	}
	
	@Test
	public void testComposablePointcut() {
		Worker worker = new Worker(); 

		NameMatchMethodPointcut one = new NameMatchMethodPointcut();
		one.addMethodName("work");
		
		ComposablePointcut pc = new ComposablePointcut((Pointcut)one);
		
		JdkRegexpMethodPointcut two = new JdkRegexpMethodPointcut();
		two.setPattern(".*Hour.*");
		
		pc.union((Pointcut)two);
		//pc.intersection((Pointcut)two);
		
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(worker);
		Worker proxy= (Worker)pf.getProxy();
		
		proxy.work();
		proxy.workHour(1);
		
	}
	
	
}
