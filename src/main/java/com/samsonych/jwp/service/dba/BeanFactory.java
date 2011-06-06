package com.samsonych.jwp.service.dba;

import is.ida.lib.spring.SpringApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BeanFactory {

	private static final String DEFAULT_CONFIG_LOCATION = "classpath:applicationContext.xml";

	protected BeanFactory() {
	}

	public static void setApplicationContext(
			final ApplicationContext applicationContext) {
		SpringApplicationContext.setApplicationContext(applicationContext);
	}

	public static Object getManager(final String manager) {
		if (SpringApplicationContext.instance() == null) {
			setApplicationContext(new ClassPathXmlApplicationContext(
					DEFAULT_CONFIG_LOCATION));
		}
		return SpringApplicationContext.instance().getBean(manager);
	}

}
