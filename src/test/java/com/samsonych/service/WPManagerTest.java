/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;

/**
 * @author samsonov
 *
 */
public class WPManagerTest extends AppJUnit4SpringContextTests {

	/**
	 * Test method for
	 * {@link com.samsonych.service.WPManager#deleteTaxonomies(java.lang.String[])}
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
