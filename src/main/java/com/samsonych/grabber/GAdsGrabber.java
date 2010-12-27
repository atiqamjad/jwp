/**
 *
 */
package com.samsonych.grabber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author samsonov
 *
 */
public class GAdsGrabber {

	private CharBuffer charBuffer;

	public GAdsGrabber(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();

			// Get a CharBuffer from the source file
			ByteBuffer bb;

			bb = fc.map(MapMode.READ_ONLY, 0, (int) fc.size());
			Charset cs = Charset.forName("8859_1");
			CharsetDecoder cd = cs.newDecoder();
			CharBuffer cb = cd.decode(bb);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String findMatchFromCharBuffer(String regexp) {
		Pattern p = Pattern.compile(regexp, Pattern.MULTILINE);
		// Run some matches
		Matcher m = p.matcher(charBuffer);
		while (m.find())
			System.out.println("Found comment: " + m.group());
		return null;
	}

	public String getPostTitle() {
		return findMatchFromCharBuffer("^<h1\\>(.*)<\\/h1>$");
	}

	public String getPostContent() {
		return findMatchFromCharBuffer("php");
	}

}
