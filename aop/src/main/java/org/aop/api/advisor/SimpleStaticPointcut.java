package org.aop.api.advisor;

import java.lang.reflect.Method;

import org.aop.api.bean.Worker;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
	@Override
	public boolean matches(Method method, Class<?> cls) {
		return ("work".equals(method.getName()));
	}

	@Override
	public ClassFilter getClassFilter() {
		return cls -> (cls == Worker.class);
	}
}