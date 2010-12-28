/**
 * $HeadURL: http://svn.isida.by:3690/svn/is.acps/branches/is.acps_wtp/solutions/dbaccess/services/core/projects/lib/src/isida/acps/service/dbaccess/core/dbmanager/AppManagerFactory.java $
 * $Revision: 1187 $
 * $Date:: 2010-11-04 10:47:52 +0200 #$
 *
 * Copyright (c) Isida-Informatica, Ltd. All Rights Reserved.
 */
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
