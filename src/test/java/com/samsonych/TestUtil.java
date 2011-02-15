package com.samsonych;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import com.samsonych.service.GAdsGrabber;
import com.samsonych.service.IWPGrabber;

public class TestUtil {
	public static final String TEST_FILE = "/7777.php";

	public static IWPGrabber initGrabber() throws ServiceException {
		IWPGrabber result = new GAdsGrabber();
		result.grab(getTestFile());
		return result;
	}

	public static File getTestFile() {
		try {
			URI uri = Runtime.getRuntime().getClass().getResource(TEST_FILE)
					.toURI();
			return new File(uri);
		} catch (URISyntaxException ex) {
		}
		return null;
	}

}