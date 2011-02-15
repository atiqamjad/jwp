package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.samsonych.TestUtil;
import com.samsonych.util.ContentUtilTest;

public class GAdsGrabberTest {
	protected static IWPGrabber grabber;

	@Before
	public void initialize() throws ServiceException {
		grabber = TestUtil.initGrabber();
	}

	@Test
	public final void testGetPostTitle() {
		Assert.assertEquals(ContentUtilTest.EXPECTED_TITLE,
				grabber.getPostTitle());
	}

	@Test
	public final void testGetPostName() {
		Assert.assertEquals(ContentUtilTest.EXPECTED_POST_NAME,
				grabber.getPostName());
	}

	@Test
	public final void testGetPostContent() {
		Assert.assertTrue(grabber.getPostContent().lastIndexOf(
				"Ok, let's face it") != -1);
	}

	@Test
	public final void testGetMetaKeywords() {
		Assert.assertTrue(grabber.getMetaKeywords().endsWith(
				"what is Credit, Credit Information"));
	}

	@Test
	public final void testGetMetaDescription() {
		Assert.assertTrue(grabber.getMetaDescription().endsWith(
				"articles and information on Credit"));
	}

}
