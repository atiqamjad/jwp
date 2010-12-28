package com.samsonych.grabber;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class GAdsGrabberTest {

    private static final String TEST_FILE = "/7777.php";

	private static final String EXPECTED_TITLE = "Negotiating Rates with Your Credit Card Company";

    private static GAdsGrabber grabber;

    @Before
    public void init() throws URISyntaxException {
        URL resource = getClass().getResource(TEST_FILE);
		grabber = new GAdsGrabber(new File(resource.toURI()));
    }

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

}
