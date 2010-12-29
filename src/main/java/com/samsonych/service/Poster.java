/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.samsonych.grabber.GAdsGrabber;
import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;

/**
 * @author samsonov
 * 
 */
public class Poster {

    private static Logger LOG = Logger.getLogger(Poster.class);
    private static WPManager manager = new WPManager();

    private String niche;

    private final List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();

    public Poster(final String niche) {
        this.niche = niche;
        try {
            taxonomies.add(addNicheCategory());
        } catch (ServiceException ex) {
            LOG.error("addNicheCategory problem", ex);
        }
        // taxonomies.addAll(addNicheTags());
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
        post.setPostTitle(grabber.getPostTitle());
        post.setPostContent(grabber.getPostContent());
        post.setTaxonomies(taxonomies);
        LOG.trace(String.format("Processing niche file [%s/%s] ...", niche, file.getName()));
        return post;
    }

    public Taxonomy addNicheCategory() throws ServiceException {
        String category = niche;
        String slug = WordUtils.capitalizeFully(niche);
        String desc = niche;
        return manager.saveOrUpdateTaxonomy(Taxonomy.createCategory(category, slug, desc));
    }

    public List<Taxonomy> addNicheTags() {
        return null;
    }
}
