/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.samsonych.grabber.GAdsGrabber;
import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.User;

/**
 * @author samsonov
 * 
 */
public class Poster {

    private static final char[] DELIMITERS = new char[] { '-', '_', '.' };
    private static Logger LOG = Logger.getLogger(Poster.class);
    private static WPManager wpManager = new WPManager();

    private String niche;

    private final List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();

    public Poster(final String niche) {
        this.niche = niche;
    }

    public String getNiche() {
        return niche;
    }

    public void setNiche(final String niche) {
        this.niche = niche;
    }

    public Post getPostFromFile(final File file) {
        GAdsGrabber grabber = new GAdsGrabber(file);

        Post post = Post.createDefaultPost();
        post.setPostAuthor(getAuthor());
        post.setPostTitle(grabber.getPostTitle());
        post.setPostName(grabber.getPostName());
        post.setPostContent(grabber.getPostContent());
        taxonomies.addAll(addNicheTags(grabber.getMetaKeywords()));
        post.setTaxonomies(taxonomies);
        LOG.trace(String.format("Processing niche file [%s/%s] ...", niche, file.getName()));
        return post;
    }

    private User getAuthor() {
        // TODO get random user
        User author = null;
        try {
            author = wpManager.getUserById(1L);
        } catch (ServiceException ex) {
            LOG.error("getUserById error", ex);
        }
        return author;
    }

    public Taxonomy addNicheCategory() {
        String slug = WordUtils.capitalizeFully(niche, DELIMITERS);
        String desc = String.format("Description for category '%s'", niche);
        Taxonomy result = Taxonomy.createCategory(niche, slug, desc);
        try {
            result = wpManager.saveOrUpdateTaxonomy(result);
        } catch (ServiceException ex) {
            LOG.error("addNicheCategory error", ex);
        }
        return result;
    }

    List<Taxonomy> addNicheTags(final String... keywords) {
        // get tags from niche
        Set<String> tags = new HashSet<String>(getTagsFromString(niche));
        // get tags from keywords
        for (String keyword : keywords) {
            tags.addAll(getTagsFromString(keyword));
        }
        // create wp tags
        List<Taxonomy> result = new ArrayList<Taxonomy>(tags.size());
        for (String tag : tags) {
            if (StringUtils.isEmpty(tag))
                continue;
            String slug = WordUtils.capitalizeFully(tag);
            String desc = String.format("Description for tag '%s'", tag);
            result.add(Taxonomy.createTag(tag, slug, desc));
        }
        try {
            result = wpManager.saveOrUpdateTaxonomyAll(result);
        } catch (ServiceException ex) {
            LOG.error("addNicheTags error", ex);
        }
        return result;
    }

    private List<String> getTagsFromString(final String str) {
        return Arrays.asList(str.toLowerCase().split("[-_\\s,:]"));
    }
}
