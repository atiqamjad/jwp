/**
 * $HeadURL: http://svn.isida.by:3690/svn/is.acps/trunk/systems/is.acps/solutions/dbaccess/services/core/projects/lib/src/isida/acps/service/dbaccess/core/dbmanager/BaseDBManagerImpl.java $
 * $Revision: 764 $
 * $Date:: 2010-10-08 11:35:07 +0300 #$
 *
 * Copyright (c) Isida-Informatica, Ltd. All Rights Reserved.
 */
package com.samsonych.service;

import is.ida.dao.IBaseDAO;
import is.ida.service.manager.DBManagerImpl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base implementation for work with persistent objects.
 * 
 * @author S.Samsonov
 * @version $Id: BaseDBManagerImpl.java 764 2010-10-08 08:35:07Z homyakov $
 */
@SuppressWarnings("unchecked")
@Component
@Transactional
public class BaseDBManagerImpl extends DBManagerImpl {

    public IBaseDAO getBaseDAO() {
        return (IBaseDAO) super.getDao();
    }

    public void setBaseDAO(final IBaseDAO dao) {
        super.setDao(dao);
    }

    /**
     * Устанавливает класс персистентного объекта. Устанавливается мануально в базовом менеджере.
     * Необходим для порождения DBManager'ов, не декларированных спринговыми DAO и DBManager-бинами
     * для соответствующих персистентных классов, определённых в генериках.
     * 
     * @param clazz
     *            класс персистентного объекта
     * @return менеджер
     */
    public BaseDBManagerImpl setPersistentClass(final Class clazz) {
        super.getDao().setPersistentClass(clazz);
        return this;
    }

} // class
