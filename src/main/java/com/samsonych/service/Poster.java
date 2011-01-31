/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	private static final String CAT_SLUG = "%s";
	private static final String CAT_DESC = "Description for category '%s'";
	private static final String TAG_SLUG = "tag-%s";
	private static final String TAG_DESC = "Description for tag '%s'";
	private static final char[] DELIMITERS = new char[] { '-', '_', '.' };
	private static Logger LOG = Logger.getLogger(Poster.class);
	private static WPManager wpManager = new WPManager();
	private static Set<String> stopWords;

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
		LOG.trace(String.format("Processing niche file [%s/%s] ...", niche,
				file.getName()));
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
		Taxonomy result = null;
		try {
			// check if category exist
			result = wpManager.getTaxonomyCategory(category);
			if (result == null) {
				// create new category
				String slug = String.format(CAT_SLUG, category.toLowerCase());
				String desc = String.format(CAT_DESC, category);
				Taxonomy cat = Taxonomy.createCategory(category, slug, desc);
				result = wpManager.saveOrUpdateTaxonomy(cat);
			}
		} catch (ServiceException ex) {
			LOG.error("addNicheCategory error", ex);
		}
		result.setCount(result.getCount() + 1);
		return result;
	}

	List<Taxonomy> addNicheTags(final String... keywords) {
		// get tags from niche
		Set<String> tags = new HashSet<String>(getTagsFromString(niche));

		// get tags from keywords
		for (String keyword : keywords) {
			tags.addAll(getTagsFromString(keyword));
		}

		InputStream in = getClass().getResourceAsStream("/stop_words.txt");
		tags.removeAll(getStopWords(in));

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
				result.add(taxonomyTag);
			}
			result = wpManager.saveOrUpdateTaxonomyAll(result);
		} catch (ServiceException ex) {
			LOG.error("addNicheTags error", ex);
		}
		return result;
	}

	public static Set<String> getStopWords(InputStream inFile) {

		if (stopWords != null)
			return stopWords;

		stopWords = new HashSet<String>();

		BufferedReader bf = null;
		try {

			bf = new BufferedReader(new InputStreamReader(inFile));
			while (bf.ready()) {
				stopWords.add(bf.readLine());
			}
		} catch (FileNotFoundException ex) {
			LOG.error("File with stop words not found!", ex);
		} catch (IOException ex) {
			LOG.error("I/O problem into stop words file!", ex);
		}
		return stopWords;
	}

	public static WPManager getWpManager() {
		return wpManager;
	}

	private List<String> getTagsFromString(final String str) {
		return Arrays.asList(str.toLowerCase().split("[-_\\s,:]"));
	}

}
