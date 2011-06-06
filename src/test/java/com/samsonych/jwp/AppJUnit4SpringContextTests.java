package com.samsonych.jwp;

import is.ida.lib.service.exception.ServiceException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.samsonych.jwp.service.IWPGrabber;
import com.samsonych.jwp.service.dba.BaseDBManagerImpl;
import com.samsonych.jwp.service.dba.DBManagerFactory;

/**
 * @author S.Samsonov
 * @version $Id: ACPSJUnit4SpringContextTests.java 1014 2010-10-23 11:52:30Z
 *          homyakov $
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
abstract public class AppJUnit4SpringContextTests extends
		AbstractJUnit4SpringContextTests {

	protected Logger log = Logger.getLogger(getClass());

	protected static BaseDBManagerImpl baseDBManager = DBManagerFactory.getBaseDBManager();
	protected static IWPGrabber grabber;

//	@Before
	@BeforeClass
	public static void initialize() throws ServiceException {
		//DBManagerFactory.setApplicationContext(applicationContext);
		/*
		if (baseDBManager == null) {
			baseDBManager = DBManagerFactory.getBaseDBManager();
			grabber = TestUtil.initGrabber();
		}
		*/
		grabber = TestUtil.initGrabber();
	}
}
