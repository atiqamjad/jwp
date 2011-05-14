package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
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

	// @Test
	@Ignore
	public final void testProcessContent() throws IOException {
		String test = FileUtils.readFileToString(TestUtil
				.getTestFile("test1.txt"));
		String expected = FileUtils.readFileToString(TestUtil
				.getTestFile("test1_expected.txt"));
		String actual = GAdsGrabber.processContent(test);
		System.out.println(actual);
		Assert.assertEquals(expected.length(), actual.length());
	}
}
