package com.samsonych.jwp.util;

import static com.samsonych.jwp.util.ContentUtil.STOP_WORDS_TXT;
import static com.samsonych.jwp.util.ContentUtil.getSingletonStopWords;
import static com.samsonych.jwp.util.ContentUtil.getTagsFromString;
import static com.samsonych.jwp.util.ContentUtil.loadStopWordsFromFile;
import static com.samsonych.jwp.util.ContentUtil.normalizePostName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class ContentUtilTest {
	public static final String UNFILTERED_FAKE_POST_NAME = "\"'~[ 350  /Negotiating: Rates ----&$ *%(with) Your... = +Credit, -<Card> !!!Company?]\";";
	public static final String EXPECTED_POST_NAME = "350-Negotiating-Rates-With-Your-Credit-Card-Company";
	public static final String EXPECTED_TITLE = "350 Negotiating Rates with Your Credit Card Company?";

	static Set<String> stopWords = null;
	static File stopWordsFile = new File("target/test-classes" + STOP_WORDS_TXT);

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		createTestStopListFile(stopWordsFile);
		ClassPathResource resource = new ClassPathResource(STOP_WORDS_TXT);
		stopWords = loadStopWordsFromFile(resource);
	}

	@AfterClass
	public static void tearDown() {
		if (stopWordsFile != null) {
			stopWordsFile.delete();
		}
	}

	@Test
	public void testNormalizePostName() {
		Assert.assertEquals(EXPECTED_POST_NAME,
				normalizePostName(UNFILTERED_FAKE_POST_NAME));
	}

	@Test
	public void testGetStopWords() {
		Assert.assertEquals(2, stopWords.size());
	}

	@Test
	public void testGetSingletonStopWords() {
		Assert.assertTrue(getSingletonStopWords().size() > 0);
	}

	@Test
	public void testGetTagsFromString() {
		Set<String> tags = new HashSet<String>(
				getTagsFromString(UNFILTERED_FAKE_POST_NAME));
		Assert.assertEquals(7, tags.size());
		tags.removeAll(stopWords);
		Assert.assertEquals(5, tags.size());
	}

	private static void createTestStopListFile(final File file)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.println("with");
		writer.println("your");
		writer.println("with");
		writer.println("your");
		writer.close();
	}

}
