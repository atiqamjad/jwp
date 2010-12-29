package com.samsonych.grabber;

import junit.framework.Assert;

import org.junit.Test;

import com.samsonych.service.AppJUnit4SpringContextTests;

public class GAdsGrabberTest extends AppJUnit4SpringContextTests{

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

}
