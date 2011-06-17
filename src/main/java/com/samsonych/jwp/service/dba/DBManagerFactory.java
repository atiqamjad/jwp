package com.samsonych.jwp.service.dba;

import is.ida.service.manager.BeanFactory;

public final class DBManagerFactory extends BeanFactory {

	public static final String BASE_MANAGER_BEAN = "baseDBManager";

	private DBManagerFactory() {
	}

	public static BaseDBManagerImpl getBaseDBManager() {
		return (BaseDBManagerImpl) getManager(BASE_MANAGER_BEAN);
	}

	public static <T> BaseDBManagerImpl getBaseDBManager(
			final Class<?> persistentClass) {
		return ((BaseDBManagerImpl) getManager(BASE_MANAGER_BEAN))
				.setPersistentClass(persistentClass);
	}

}
