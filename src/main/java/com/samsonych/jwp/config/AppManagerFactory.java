package com.samsonych.jwp.config;

import is.ida.service.manager.BeanFactory;

public final class AppManagerFactory extends BeanFactory {

	private AppManagerFactory() {
	}

	public static AppConfig getAppConfig() {
		return (AppConfig) getManager("appConfig");
	}

}
