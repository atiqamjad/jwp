/**
 *
 */
package com.samsonych.grabber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author samsonov
 * 
 */
public class GAdsGrabber implements IWPGrabber {

    private static Logger LOG = Logger.getLogger(GAdsGrabber.class);

    private CharBuffer charBuffer;

    public GAdsGrabber(final File file) {
        LOG.debug(String.format("Grabing file - %s ", file.getAbsolutePath()));
        try {
            charBuffer = getBuffer(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    private String findMatchFromCharBuffer(final String regexp) {
        Pattern p = Pattern.compile(regexp, Pattern.MULTILINE);
        // Run some matches
        Matcher m = p.matcher(charBuffer);
        String result = null;
        if (m.find()) {
            result = m.group(1);
        }
        return result;
    }

    private CharBuffer getBuffer(final InputStream is) {
        try {
            FileChannel fc = ((FileInputStream) is).getChannel();
            // Get a CharBuffer from the source file
            ByteBuffer bb = fc.map(MapMode.READ_ONLY, 0, (int) fc.size());
            Charset cs = Charset.forName("8859_1");
            return cs.newDecoder().decode(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Getting content of the post. */
    public String getPostContent() {
        String string = charBuffer.toString();
        String startStr = "<?php include(\"inc/ads-top.inc\"); ?>";
        String endStr = "<?php include(\"inc/ads-body.inc\"); ?>";
        int startIndex = string.indexOf(startStr) + startStr.length();
        int endIndex = string.indexOf(endStr);
        // with delete CR's
        String result = string.substring(startIndex, endIndex).replaceAll("\r|\n", "");
        LOG.trace(String.format("Post content [%s]", result));
        LOG.debug("post content size = " + result.length());
        return result;
    }

    public String getPostTitle() {
        String regexp = "^<h1\\>(.*)<\\/h1>$";
        String result = findMatchFromCharBuffer(regexp);
        LOG.debug(String.format("Title post = '%s'", result));
        return result;
    }

    public String clearTrash(final String str) {
        // delete '<p></p>'
        // - <br />
        // <a href="">
        return str;
    }

    public String getPostName() {
        return getPostTitle().replaceAll("[ |_|:|,]", "-").toLowerCase();
    }

    public String getMetaKeywords() {
        String regexp = "^<meta name=\"Keywords\" content=\"(.*)\">$";
        String result = findMatchFromCharBuffer(regexp);
        LOG.debug(String.format("Meta keywords post = '%s'", result));
        return result;
    }

    public String getMetaDescription() {
        String regexp = "^<meta name=\"Description\" content=\"(.*)\">$";
        String result = findMatchFromCharBuffer(regexp);
        LOG.debug(String.format("Meta description post = '%s'", result));
        return result;
    }

}
