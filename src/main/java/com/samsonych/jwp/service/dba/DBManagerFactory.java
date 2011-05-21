package com.samsonych.jwp.service.dba;

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
