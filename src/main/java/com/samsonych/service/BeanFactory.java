/**
 * $HeadURL: http://svn.isida.by:3690/svn/is.acps/trunk/systems/is.acps/solutions/dbaccess/services/core/projects/lib/src/isida/acps/service/dbaccess/core/dbmanager/BeanFactory.java $
 * $Revision: 1185 $
 * $Date:: 2010-11-03 20:50:56 +0200 #$
 *
 * Copyright (c) Isida-Informatica, Ltd. All Rights Reserved.
 */
package com.samsonych.service;

import is.ida.lib.spring.SpringApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Суперкласс фабрик, инициализирующих спринговых бинов.
 *
 * @author S.Samsonov
 * @version $Id: BeanFactory.java 1185 2010-11-03 18:50:56Z samsonov $
 */
public abstract class BeanFactory {

    private static final String ACPS_CONFIG_LOCATION = "classpath:/WEB-INF/classes/applicationContext.xml";

    protected BeanFactory() {
    }

    /**
     * <p>
     * Устанавливает Spring-контекст приложения.
     * </p>
     * <p>
     * Если работа со Spring Beans будет в web-приложении, то его (контекст) желательно определять
     * через метод WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContext sc) в
     * методе contextInitialized класса, который инициализирует приложение (ApplicationInitializer).
     * </p>
     * <p>
     * В других случаях контекст определяется через ClassPathXmlApplicationContext или
     * FileSystemXmlApplicationContext.
     * </p>
     *
     * @param applicationContext
     *            Spring-контекст приложения
     * @see org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext
     * @see org.springframework.context.support.ClassPathXmlApplicationContext
     * @see org.springframework.context.support.FileSystemXmlApplicationContext
     */
    public static void setApplicationContext(final ApplicationContext applicationContext) {
        SpringApplicationContext.setApplicationContext(applicationContext);
    }

    /**
     * Возвращает по идентификатору менеджера экземпляр Spring-бина.
     *
     * @param manager
     *            идентификатор менеджера
     * @return экземпляр Spring-бина
     */
    public static Object getManager(final String manager) {
        ApplicationContext context = SpringApplicationContext.instance();
        if (context == null) {
            setApplicationContext(new ClassPathXmlApplicationContext(ACPS_CONFIG_LOCATION));
        }
        return context.getBean(manager);
    }

}
