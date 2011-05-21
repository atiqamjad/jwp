package com.samsonych.jwp;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import com.samsonych.jwp.service.IWPGrabber;
import com.samsonych.project.gads.GAdsGrabber;

public class TestUtil {
	public static final String TEST_FILE = "7777.php";

	public static IWPGrabber initGrabber() throws ServiceException {
		IWPGrabber result = new GAdsGrabber();
		result.grab(getGadsTestFile());
		return result;
	}

	public static File getGadsTestFile() {
		return getTestFile(TEST_FILE);
	}

	public static File getTestFile(String fileName) {
		try {
			URI uri = Runtime.getRuntime().getClass()
					.getResource("/" + fileName).toURI();
			return new File(uri);
		} catch (URISyntaxException ex) {
		}
		return null;
	}

}