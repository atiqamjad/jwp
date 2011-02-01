package com.samsonych.grabber;

import junit.framework.Assert;

import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;

public class GAdsGrabberTest extends AppJUnit4SpringContextTests {

	public static final String EXPECTED_TITLE = "Negotiating Rates with Your Credit Card Company";
	public static final String UNFILTERED_POST_NAME = "\"'~[   Negotiating: Rates ----&$ *%(with) Your... = +Credit, -Card !!!Company?]\";";
	public static final String EXPECTED_POST_NAME = "negotiating-rates-with-your-credit-card-company";

	@Test
	public final void testGetPostTitle() {
		Assert.assertEquals(EXPECTED_TITLE, grabber.getPostTitle());
	}

	@Test
	public final void testGetPostName() {
		Assert.assertEquals(EXPECTED_TITLE, grabber.getPostTitle());
	}

	@Test
	public final void testNormalizePostName() {
		Assert.assertEquals(EXPECTED_POST_NAME,
				grabber.normalizePostName(UNFILTERED_POST_NAME));
	}

	@Test
	public final void testGetPostContent() {
		String content = grabber.getPostContent();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.lastIndexOf("Ok, let's face it") != -1);
	}

	@Test
	public final void testGetMetaKeywords() {
		Assert.assertTrue(grabber.getMetaKeywords().startsWith(EXPECTED_TITLE));
	}

	@Test
	public final void testGetMetaDescription() {
		Assert.assertTrue(grabber.getMetaDescription().endsWith(
				"articles and information on Credit"));
	}

}
