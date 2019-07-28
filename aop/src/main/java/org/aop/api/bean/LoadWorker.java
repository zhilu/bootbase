package org.aop.api.bean;

public class LoadWorker  implements IWorker{
	public void work() {
		int sum =0;
		for (int i = 0; i < 100000; i++) {
			sum+=i;
		}
		System.out.println("workout "+ sum);
	}
}
