/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.User;
import com.samsonych.util.ContentUtil;

/**
 * @author samsonov
 * 
 */
public class Poster {

    private static final String CAT_SLUG = "%s";
    private static final String CAT_DESC = "Description for category '%s'";
    private static final String TAG_SLUG = "tag-%s";
    private static final String TAG_DESC = "Description for tag '%s'";
    private static final char[] DELIMITERS = new char[] { '-', '_', '.' };
    private static Logger LOG = Logger.getLogger(Poster.class);
    private static WPManager wpManager = new WPManager();

    private String niche;

    private Taxonomy nicheCategory;

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
        post.setTaxonomies(addNicheTags(grabber.getMetaKeywords()));
        post.getTaxonomies().add(nicheCategory);
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
        String category = WordUtils.capitalizeFully(niche, DELIMITERS);
        try {
            // check if category exist
            nicheCategory = wpManager.getTaxonomyCategory(category);
            if (nicheCategory == null) {
                // create new category
                String slug = String.format(CAT_SLUG, category.toLowerCase());
                String desc = String.format(CAT_DESC, category);
                nicheCategory = wpManager.saveOrUpdateTaxonomy(Taxonomy.createCategory(category,
                        slug, desc));
            }
        } catch (ServiceException ex) {
            LOG.error("addNicheCategory error", ex);
        }
        nicheCategory.setCount(nicheCategory.getCount() + 1);
        return nicheCategory;
    }

    List<Taxonomy> addNicheTags(final String... keywords) {
        // get tags from niche
        Set<String> tags = new HashSet<String>(ContentUtil.getTagsFromString(niche));

        // get tags from keywords
        for (String keyword : keywords) {
            tags.addAll(ContentUtil.getTagsFromString(keyword));
        }

        InputStream fin = getClass().getResourceAsStream("/stop_words.txt");
        tags.removeAll(ContentUtil.getStopWords(fin));

        // create wp tags
        List<Taxonomy> result = new ArrayList<Taxonomy>(tags.size());
        try {
            for (String tag : tags) {
                if (StringUtils.isEmpty(tag))
                    continue;
                // check if tag exist
                tag = WordUtils.capitalizeFully(tag);
                Taxonomy taxonomyTag = wpManager.getTaxonomyTag(tag);
                if (taxonomyTag == null) {
                    // create new tag
                    String slug = String.format(TAG_SLUG, tag.toLowerCase());
                    String desc = String.format(TAG_DESC, tag);
                    taxonomyTag = Taxonomy.createTag(tag, slug, desc);
                }
                taxonomyTag.setCount(taxonomyTag.getCount() + 1);
                result.add(taxonomyTag);
            }
            result = wpManager.saveOrUpdateTaxonomyAll(result);
        } catch (ServiceException ex) {
            LOG.error("addNicheTags error", ex);
        }
        return result;
    }

    public static WPManager getWpManager() {
        return wpManager;
    }

}
