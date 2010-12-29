/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Taxonomy category = Taxonomy.createCategory(niche, WordUtils.capitalizeFully(niche), niche);
        try {
            category = wpManager.saveOrUpdateTaxonomy(category);
        } catch (ServiceException ex) {
            LOG.error("addNicheCategory error", ex);
        }
        return category;
    }

    List<Taxonomy> addNicheTags(final String keywords) {
        List<Taxonomy> tags = Collections.EMPTY_LIST;
        return tags;
    }
}
