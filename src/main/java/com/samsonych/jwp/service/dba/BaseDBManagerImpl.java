package com.samsonych.jwp.service.dba;

import is.ida.dao.IBaseDAO;
import is.ida.service.manager.DBManagerImpl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BaseDBManagerImpl extends DBManagerImpl {

	public IBaseDAO<Serializable, Serializable> getBaseDAO() {
		return (IBaseDAO) super.getDao();
	}

	public void setBaseDAO(final IBaseDAO dao) {
		super.setDao(dao);
	}

	public BaseDBManagerImpl setPersistentClass(final Class<?> clazz) {
		super.getDao().setPersistentClass(clazz);
		return this;
	}

} // class
