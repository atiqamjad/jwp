/**
 * $HeadURL: http://svn.isida.by:3690/svn/is.acps/trunk/systems/is.acps/solutions/dbaccess/services/core/projects/lib/src/isida/acps/service/dbaccess/core/dbmanager/DBManagerFactory.java $
 * $Revision: 1216 $
 * $Date:: 2010-11-04 21:39:41 +0200 #$
 *
 * Copyright (c) Isida-Informatica, Ltd. All Rights Reserved.
 */
package com.samsonych.service;

/**
 * Фабрика DB-менеджеров (Spring Beans).
 * 
 * @author S.Samsonov
 * @version $Id: DBManagerFactory.java 1216 2010-11-04 19:39:41Z homyakov $
 */
public final class DBManagerFactory extends BeanFactory {

    public static final String BASE_MANAGER_BEAN = "baseDBManager";

    private DBManagerFactory() {
    }

    public static BaseDBManagerImpl getBaseDBManager() {
        return (BaseDBManagerImpl) getManager(BASE_MANAGER_BEAN);
    }

    public static <T> BaseDBManagerImpl getBaseDBManager(final Class<T> persistentClass) {
        return ((BaseDBManagerImpl) getManager(BASE_MANAGER_BEAN))
                .setPersistentClass(persistentClass);
    }

}
