package org.aop.api.hello;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAOPDemo {
	public static void main(String... args) {
		Agent target = new Agent();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new AgentDecorator());
		pf.setTarget(target);
		Agent proxy = (Agent) pf.getProxy();
		target.speak();
		System.out.println("");
		proxy.speak();
		System.out.println("");
		proxy.toString();
		
	}
}