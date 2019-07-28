package org.aop.api.introdution;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

	private static final long serialVersionUID = -8972250575537147735L;

	public IsModifiedAdvisor() {
		super(new IsModifiedMixin());
	}
}