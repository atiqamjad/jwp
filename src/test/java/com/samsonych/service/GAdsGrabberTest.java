package com.samsonych.service;

import junit.framework.Assert;

import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;
import com.samsonych.util.ContentUtilTest;

public class GAdsGrabberTest extends AppJUnit4SpringContextTests {
    @Test
    public final void testGetPostTitle() {
        Assert.assertEquals(ContentUtilTest.EXPECTED_TITLE, grabber.getPostTitle());
    }

    @Test
    public final void testGetPostName() {
        Assert.assertEquals(ContentUtilTest.EXPECTED_POST_NAME, grabber.getPostName());
    }

    @Test
    public final void testGetPostContent() {
        Assert.assertTrue(grabber.getPostContent().lastIndexOf("Ok, let's face it") != -1);
    }

    @Test
    public final void testGetMetaKeywords() {
        Assert.assertTrue(grabber.getMetaKeywords().endsWith("what is Credit, Credit Information"));
    }

    @Test
    public final void testGetMetaDescription() {
        Assert.assertTrue(grabber.getMetaDescription().endsWith(
                "articles and information on Credit"));
    }

}
