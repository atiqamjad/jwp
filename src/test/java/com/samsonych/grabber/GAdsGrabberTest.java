package com.samsonych.grabber;

import java.io.File;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

public class GAdsGrabberTest {

	private static final String EXPECTED_TITLE = "Negotiating Rates with Your Credit Card Company";

	private final GAdsGrabber grabber = new GAdsGrabber(new File("7777.php"));

	@Test
	public final void testGetPostTitle() {
		String title = grabber.getPostTitle();
		Assert.assertNotNull(title);
		Assert.assertEquals(EXPECTED_TITLE, title);
	}

	@Test
	@Ignore
	public final void testGetPostContent() {
		String content = grabber.getPostContent();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.lastIndexOf("Ok, let's face it") != -1);
	}

}
