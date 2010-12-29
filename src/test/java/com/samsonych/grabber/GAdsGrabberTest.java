package com.samsonych.grabber;

import junit.framework.Assert;

import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;

public class GAdsGrabberTest extends AppJUnit4SpringContextTests {

	private static final String EXPECTED_TITLE = "Negotiating Rates with Your Credit Card Company";

	@Test
	public final void testGetPostTitle() {
		String title = grabber.getPostTitle();
		Assert.assertNotNull(title);
		Assert.assertEquals(EXPECTED_TITLE, title);
	}

	@Test
	public final void testGetPostContent() {
		String content = grabber.getPostContent();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.lastIndexOf("Ok, let's face it") != -1);
	}

	@Test
	public final void testGetMetaKeywords() {
		String keywords = grabber.getMetaKeywords();
		Assert.assertNotNull(keywords);
		Assert.assertTrue(keywords.startsWith(EXPECTED_TITLE));
	}

	@Test
	public final void testGetMetaDescription() {
		String desc = grabber.getMetaDescription();
		Assert.assertNotNull(desc);
		Assert.assertTrue(desc.endsWith("articles and information on Credit"));
	}

}
