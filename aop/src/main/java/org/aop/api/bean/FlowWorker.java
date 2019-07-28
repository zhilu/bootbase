package org.aop.api.bean;

public class FlowWorker {
	
	private Worker worker;

	public FlowWorker(Worker worker) {
		this.worker = worker;
	}
	
	public void work() {
		System.out.print("flow worker -->");
		worker.work();
	}
}
