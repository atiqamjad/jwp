/**
 *
 */
package com.samsonych.jwp.service;

import is.ida.lib.service.exception.ServiceException;

import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.jwp.AppJUnit4SpringContextTests;
import com.samsonych.jwp.service.WPManager;

/**
 * @author samsonov
 * 
 */
@Ignore
public class WPManagerTest extends AppJUnit4SpringContextTests {

	/**
	 * Test method for
	 * {@link com.samsonych.jwp.jwp.service.WPManager#deleteTaxonomies(java.lang.String[])}
	 * .
	 * 
	 * @throws ServiceException
	 */
	@Test
	@Ignore
	public final void testDeleteTaxonomies() throws ServiceException {
		new WPManager().deleteTaxonomies("your");
	}

}
