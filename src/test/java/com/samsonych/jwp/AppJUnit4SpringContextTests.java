package com.samsonych.jwp;

import is.ida.lib.service.exception.ServiceException;
import is.ida.service.manager.AppContext;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.samsonych.jwp.service.Poster;
import com.samsonych.jwp.service.WPManager;
import com.samsonych.jwp.service.dba.BaseDBManagerImpl;
import com.samsonych.jwp.service.dba.DBManagerFactory;

/**
 * 
 * @author samsonych
 * 
 */
@ContextConfiguration(locations = { "classpath:/spring/context.xml" })
abstract public class AppJUnit4SpringContextTests extends
		AbstractJUnit4SpringContextTests {

	protected Logger log = Logger.getLogger(getClass());

	private static BaseDBManagerImpl baseDBManager;

	private static Poster poster;

	public static BaseDBManagerImpl getBaseDBManager() {
		return baseDBManager;
	}

	public static Poster getPoster() {
		return poster;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws ServiceException {
		poster = new Poster(TestUtil.initGrabber());
	}

	@Before
	public void setUp() throws ServiceException {
		if (AppContext.getApplicationContext() == null) {
			AppContext.setApplicationContext(applicationContext);
			baseDBManager = DBManagerFactory.getBaseDBManager();
			poster.setWpManager(new WPManager());
		}
	}
}
