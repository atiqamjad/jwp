/**
 *
 */
package com.samsonych.jwp.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author samsonych
 *
 */
public final class ContentUtil {

	private static Logger LOG = Logger.getLogger(ContentUtil.class);
	private static Set<String> stopWords;
	public static final String STOP_WORDS_TXT = "/stop_words.txt";

	private ContentUtil() {
	}

	public static String normalizeWhitespaces(final String str) {
		return str.replaceAll("\\s+", " ").replaceAll("\\s([!?]+)", "$1")
				.trim();
	}

	public static String normalizePostName(final String str) {
		return WordUtils.capitalize(cleanWords(str).toLowerCase()).replaceAll(
				"\\s+", "-");
	}

	private static String cleanWords(final String str) {
		return str.replaceAll("[-!?\\:=\\+\\[\\]\\(\\)&%$*#~\"';.,<>/]", " ")
				.trim();
	}

	private static String deleteDigits(final String str) {
		return str.replaceAll("\\d+", "").trim();
	}

	public static List<String> getTagsFromString(final String str) {
		String[] tags = deleteDigits(cleanWords(str)).toLowerCase().split(
				"\\s+");
		return Arrays.asList(tags);
	}

	public static Set<String> getSingletonStopWords() {

		if (stopWords == null) {
			stopWords = loadStopWordsFromFile(new ClassPathResource("/conf"
					+ STOP_WORDS_TXT));
		}
		return stopWords;

	}

	public static Set<String> loadStopWordsFromFile(final Resource resource) {
		Set<String> result = new HashSet<String>();
		BufferedReader bf = null;
		try {
			FileInputStream fin = new FileInputStream(resource.getFile());
			bf = new BufferedReader(new InputStreamReader(fin));
			while (bf.ready()) {
				result.add(bf.readLine());
			}
			fin.close();
		} catch (FileNotFoundException ex) {
			LOG.error("File with stop words not found!", ex);
		} catch (IOException ex) {
			LOG.error("I/O problem into stop words file!", ex);
		}
		return result;
	}
}
