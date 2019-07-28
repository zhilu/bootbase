package org.aop.api.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class SipmleThrowAdvice implements ThrowsAdvice {

	public void afterThrowing(Exception ex) throws Throwable {
		System.out.println("Caught Any Exception: " + ex.getClass().getName());
	}

	public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException ex) throws Throwable {
		System.out.println("Caught specific Exception : " + ex.getClass().getName());
	}

}
