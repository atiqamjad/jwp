/**
 *
 */
package com.samsonych.service;

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

import com.samsonych.util.ContentUtil;

/**
 * @author samsonov
 * 
 */
public class GAdsGrabber implements IWPGrabber {

    private static Logger LOG = Logger.getLogger(GAdsGrabber.class);

    private CharBuffer charBuffer;

    @Override
    public void grab(final File file) {
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
    @Override
    public String getPostContent() {
        String string = charBuffer.toString();
        String startStr = "<?php include(\"inc/ads-top.inc\"); ?>";
        String endStr = "<?php include(\"inc/ads-body.inc\"); ?>";
        int startIndex = string.indexOf(startStr) + startStr.length();
        int endIndex = string.indexOf(endStr);
        // with delete CR's
        String result = processContent(string.substring(startIndex, endIndex));
        LOG.trace(String.format("Post content [%s]", result));
        LOG.debug("post content size = " + result.length());
        return result;
    }

    @Override
    public String getPostTitle() {
        String result = findMatchFromCharBuffer("(?msi)^<h1>(.*)<\\/h1>$");
        if (result == null) {
            result = findMatchFromCharBuffer("(?msi)^<title>(.*)<\\/title>$");
        }
        result = ContentUtil.normalizeWhitespaces(result);
        LOG.debug(String.format("Title post = '%s'", result));
        return result;
    }

    public String processContent(final String str) {
        // with delete CR's
        String result = str;
        result = result.replaceAll("(?i)<p>|<br\\s*\\/?>|\r|\n", "");
        result = result.replaceAll("(?i)<\\/p>", "\n\n");
        // <ul>
        result = result.replaceAll("(?i)<li>(.*)\n", "<li>$1</li>");

        // <ol>
        result = result.replaceAll("\\d+[.)]\\s+(.*)\n", "<li>$1</li>");      
        
        result = result.replaceFirst("(?i)<li>", "<ul>\n<li>");
        result = result.replaceFirst("(?i)<\\/li>\n{2,}", "</li>\n</ul>\n");
        
        // delete '(<a>...</a>)'
        result = result.replaceAll("(?msi)\\(\\s*<a.*?<\\/a>\\s*\\)", "");
         result = result.replaceAll("\n{3,}", "\n\n");
        return result;
    }

    @Override
    public String getMetaKeywords() {
        String regexp = "(?msi)^<meta name=\"Keywords\" content=\"(.*?)\">$";
        String result = findMatchFromCharBuffer(regexp);
        LOG.debug(String.format("Meta keywords post = '%s'", result));
        return result;
    }

    @Override
    public String getMetaDescription() {
        String regexp = "(?msi)^<meta name=\"Description\" content=\"(.*?)\">$";
        String result = findMatchFromCharBuffer(regexp);
        LOG.debug(String.format("Meta description post = '%s'", result));
        return result;
    }

    @Override
    public String getPostName() {
        return ContentUtil.normalizePostName(getPostTitle());
    }

}
