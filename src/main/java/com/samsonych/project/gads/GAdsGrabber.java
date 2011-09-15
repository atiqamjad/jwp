/**
 *
 */
package com.samsonych.project.gads;

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

import com.samsonych.jwp.service.IWPGrabber;
import com.samsonych.jwp.util.ContentUtil;

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

    public static String processContent(final String str) {
        // with delete CR's
        String result = str;

        // tab -> space
        result = result.replaceAll("\t", " ");

        result = result.replaceAll("\r|\n", " ");

        result = result.replaceAll("(\n|>|\\)) {1,}", "$1");
        result = result.replaceAll("(\\w) {2,}", "$1 ");

        // header processing
        result = result.replaceAll("(?i)<p>((\\w+\\W*){1,5})</p>", "<h3>$1</h3>\n\n");
        result = result.replaceAll("(?i)<p>|</li>|</ul>|</ol>|<br\\s*/?\\s*>", "\n");
        result = result.replaceAll("(?i)</p>", "\n\n");

        // lists processing
        // <ul> result
        result = result.replaceAll("(?i)([^\n])<li>", "$1\n<li>");
        result = result.replaceAll("(?i)<li>(.*)(<\\w+>)", "<li>$1\n$2");
        result = result.replaceAll("(?i)<li>(.*?)\\s*\n", "<li>$1</li>");
        result = result.replaceAll("(?i)<li>(.*)</li>", "\n\n<ul><li>$1</li></ul>\n\n");

        // <ol> result
        // result = result.replaceAll("1\\s*[-.)]", "\n\n<ol><li>");
        // result = result.replaceAll("\\d+\\s*[-.)]\\s*(.+?)", "<li>$1</li>");
        System.out.println("r2:" + result);

        // delete '(<a>...</a>)'
        result = result.replaceAll("(?msi)\\(\\s*<a.*?<\\/a>\\s*\\)", "");


        // 1) определить последние 2 абзаца
        // удалить абзац если в нем есть:
        // - ссылки;
        // - слова "contact","visit","author","writer", "articles?",
        // "copyright", "(c)", "&copy;", "©"
        // 2) если есть фраза "" удалить всё что после нее
        result = result.replaceAll("(?msi)\n(About The Author|[-+_#@%=$*]{4,}).*", "");

        // result = result.replaceAll("(?msi)<p>(.+?)</p>", "$1\n");
        // result = result.replaceAll("(?i)<p>|<br\\s*/?>", "");

        // define paragraphs
        // "<p>((\w+\W*[^:,.]){6})</p>"
        /*
         * try { String regex = "(\\w.+?)(?:\r?\n){2}"; Pattern pattern =
         * Pattern.compile(regex); Matcher matcher = pattern.matcher(result);
         * StringBuffer sb = new StringBuffer(); while (matcher.find()) { String
         * h3 = matcher.group(); if (StringUtils.split(h3).length < 7) { //
         * LOG.trace(h3); matcher.appendReplacement(sb,
         * "\n<span style='color:red'>$1</span>\n\n"); } } result =
         * matcher.appendTail(sb).toString(); // System.out.println(sb); //
         * String regex = "(?i)<p>((?:\\w+[-,.!?\\s]*)+)</p>";
         *
         * } catch (PatternSyntaxException ex) {
         * LOG.debug("Syntax error in the regular expression", ex); } catch
         * (IllegalArgumentException ex) {
         * LOG.debug("Syntax error in the replacement text (unescaped $ signs?)"
         * , ex); } catch (IndexOutOfBoundsException ex) {
         *
         * LOG.debug("Non-existent backreference used the replacement text",
         * ex); }
         */

        result = result.replaceAll("^\n{1,}", "");
        result = result.replaceAll("\n{3,}", "\n\n");
//        System.out.println("r3:" + result);
        return result;
    }
}
