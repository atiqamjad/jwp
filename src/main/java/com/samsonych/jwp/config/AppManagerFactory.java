package com.samsonych.jwp.config;

import com.samsonych.jwp.service.dba.BeanFactory;

public final class AppManagerFactory extends BeanFactory {

	private AppManagerFactory() {
	}

	public static AppConfig getAppConfig() {
		return (AppConfig) getManager("appConfig");
	}

}
