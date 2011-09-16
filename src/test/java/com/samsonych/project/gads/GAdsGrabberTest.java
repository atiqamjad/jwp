package com.samsonych.project.gads;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.jwp.TestUtil;
import com.samsonych.jwp.service.IWPGrabber;
import com.samsonych.jwp.util.ContentUtilTest;
import com.samsonych.project.gads.GAdsGrabber;

public class GAdsGrabberTest {

    private Logger LOG = Logger.getLogger(GAdsGrabberTest.class);

    protected static IWPGrabber grabber = TestUtil.initGrabber();

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

    private final void testProcessContent(String testFile, String expectedFile) throws IOException {
        String test = FileUtils.readFileToString(TestUtil.getTestFile("gads/" + testFile));
        String expected = FileUtils.readFileToString(TestUtil.getTestFile("gads/" + expectedFile));
        String actual = GAdsGrabber.processContent(test);
        expected = expected.replaceAll("\r", "");
        actual = actual.replaceAll("\r", "");
        LOG.debug(actual);
        Assert.assertEquals(expected.length(), actual.length());
    }

    @Test
    public final void testParseHeaders() throws IOException {
        testProcessContent("headers.txt", "headers_expected.txt");
    }

    @Test
    // @Ignore
    public final void testParseLists() throws IOException {
        testProcessContent("lists.txt", "lists_expected.txt");
    }

    @Test
     @Ignore
    public final void testParseClearFooter() throws IOException {
        testProcessContent("footer.txt", "footer_expected.txt");
    }

}
