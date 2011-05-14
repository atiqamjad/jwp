package com.samsonych;

import com.samsonych.service.dba.BeanFactory;

/**
 * Фабрика менеджеров (Spring Beans).
 *
 * @author S.Samsonov
 * @version $Id: AppManagerFactory.java 1187 2010-11-04 08:47:52Z samsonov $
 */
public final class AppManagerFactory extends BeanFactory {

	private AppManagerFactory() {
	}

	public static AppConfig getAppConfig() {
		return (AppConfig) getManager("appConfig");
	}

}
