/**
 * $HeadURL: http://svn.isida.by:3690/svn/is.acps/branches/is.acps_wtp/solutions/tests/services/junit/projects/lib/src/isida/acps/lib/tests/junit/ACPSJUnit4SpringContextTests.java $
 * $Revision: 1014 $
 * $Date:: 2010-10-23 14:52:30 +0300 #$
 *
 * Copyright (c) Isida-Informatica, Ltd. All Rights Reserved.
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.samsonych.grabber.GAdsGrabber;
import com.samsonych.service.dba.BaseDBManagerImpl;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author S.Samsonov
 * @version $Id: ACPSJUnit4SpringContextTests.java 1014 2010-10-23 11:52:30Z homyakov $
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AppJUnit4SpringContextTests extends AbstractJUnit4SpringContextTests {

    protected static final String TEST_FILE = "/7777.php";
    protected static BaseDBManagerImpl baseDBManager;
    protected static GAdsGrabber grabber;
    protected static File testFile;

    @Before
    public void initialize() throws ServiceException, URISyntaxException {
        DBManagerFactory.setApplicationContext(applicationContext);
        if (baseDBManager == null) {
            baseDBManager = DBManagerFactory.getBaseDBManager();
            testFile = new File(getClass().getResource(TEST_FILE).toURI());
            grabber = new GAdsGrabber(testFile);
        }
    }

}
