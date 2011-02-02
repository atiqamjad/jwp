package com.samsonych.util;

import static com.samsonych.util.ContentUtil.getStopWords;
import static com.samsonych.util.ContentUtil.getTagsFromString;
import static com.samsonych.util.ContentUtil.normalizePostName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class ContentUtilTest {
    public static final String UNFILTERED_FAKE_POST_NAME = "\"'~[ 350  Negotiating: Rates ----&$ *%(with) Your... = +Credit, -<Card> !!!Company?]\";";
    public static final String EXPECTED_POST_NAME = "350-Negotiating-Rates-With-Your-Credit-Card-Company";
    public static final String EXPECTED_TITLE = "350 Negotiating Rates with Your Credit Card Company?";
    @Test
    public void testNormalizePostName() {
        Assert.assertEquals(EXPECTED_POST_NAME, normalizePostName(UNFILTERED_FAKE_POST_NAME));
    }

    @Test
    public void testGetStopWords() {
        Assert.assertEquals(2, getTestStopListWords().size());
    }

    @Test
    public void testGetTagsFromString() {
        Set<String> tags = new HashSet<String>(getTagsFromString(UNFILTERED_FAKE_POST_NAME));
        Assert.assertEquals(7, tags.size());
        tags.removeAll(getTestStopListWords());
        Assert.assertEquals(5, tags.size());
    }

    private Set<String> getTestStopListWords() {
        Set<String> stopWords = null;
        try {
            File file = new File("stoplist_test.txt");
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println("with");
            writer.println("your");
            writer.println("with");
            writer.println("your");
            writer.close();
            stopWords = getStopWords(new FileInputStream(file));
            file.delete();
        } catch (FileNotFoundException ex) {
            Assert.fail(ex.getMessage());
        }
        return stopWords;
    }

}
