package cn.gyyx.framework.spring;

import org.springframework.context.ApplicationContext;

public class SpringContextUtil {
	public static ApplicationContext getContext() {
		return SpringContextHolder.getApplicationContext();
	}

	public static Object getService(String serviceName) {
		Object service = null;

		service = getContext().getBean(serviceName);
		return service;
	}

	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}
}
