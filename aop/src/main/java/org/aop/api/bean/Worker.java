package org.aop.api.bean;

import org.aop.api.advisor.AdviceRequired;

public class Worker implements IWorker{

	
	public void work() {
		System.out.println("Worker working");
	}

	@Override
	public String toString() {
		return "real worker";
	}
	
	
	public void workThrowSpecific() {
		throw new IllegalArgumentException("workThrowSpecific");
	}
	
	public void workThrowAny() {
		throw new RuntimeException("workThrowAny");
	}
	
	@AdviceRequired
	public void workHour(int num) {
		System.out.println("Worker working "+ num);
	}
	
	public void workHour(int num,double pay) {
		System.out.println("Worker working "+ num + " " + pay);
	}
	
	
}
