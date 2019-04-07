package org.aop.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class MonitorAspect {
	@Pointcut("execution(public String org.aop.PersonService.getFullName(..))")
    public void monitor() { }
    
    @Pointcut("execution(public int org.aop.PersonService.getAge(..))")
    public void myMonitor() { }
    
    @Before("monitor()")
    public void log() {
    	log.info("exec monitor()");
    }
    
    @After("myMonitor()")
    public void logAfter() {
    	log.info("exec myMonitor()");
    }
    

    
}
