package com.samsonych;

import is.ida.lib.service.exception.ServiceException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.samsonych.service.IWPGrabber;
import com.samsonych.service.dba.BaseDBManagerImpl;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author S.Samsonov
 * @version $Id: ACPSJUnit4SpringContextTests.java 1014 2010-10-23 11:52:30Z
 *          homyakov $
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
abstract public class AppJUnit4SpringContextTests extends
		AbstractJUnit4SpringContextTests {

	protected Logger log = Logger.getLogger(getClass());

	protected static BaseDBManagerImpl baseDBManager;
	protected static IWPGrabber grabber;

	@Before
	public void initialize() throws ServiceException {
		DBManagerFactory.setApplicationContext(applicationContext);
		if (baseDBManager == null) {
			baseDBManager = DBManagerFactory.getBaseDBManager();
			grabber = TestUtil.initGrabber();
		}
	}
}
