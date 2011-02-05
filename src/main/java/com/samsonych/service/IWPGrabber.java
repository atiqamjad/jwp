package com.samsonych.service;

import java.io.File;

public interface IWPGrabber {

	String getPostTitle();

	String getPostName();

	String getPostContent();

	String getMetaKeywords();

	String getMetaDescription();

    void grab(File testFile);

}
