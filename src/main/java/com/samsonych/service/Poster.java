/**
 *
 */
package com.samsonych.service;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;

import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.TaxonomyType;
import com.samsonych.model.wp.Term;
import com.samsonych.model.wp.TermTaxonomy;

/**
 * @author samsonov
 *
 */
public class Poster {

	private static Logger LOG = Logger.getLogger(Poster.class);
	private static WPManager manager = new WPManager();

	private String niche;

	private Map<Integer, TermTaxonomy> termTaxonomies;

	public Poster(String niche) {
		this.niche = niche;
		termTaxonomies.put(1, createNicheCategory(niche));
	}

	public String getNiche() {
		return niche;
	}

	public void setNiche(String niche) {
		this.niche = niche;
	}

	public void doPostFromFile(File file) {
		Post post = new Post();
		// create category
//		post.setTermTaxonomies(termTaxonomies);
		// create tags
		// create post
		LOG.trace(String.format("Processing niche file [%s/%s] ...", niche,
				file.getName()));
	}

	public TermTaxonomy createNicheCategory(String niche) {
		Term term = new Term(niche, niche);
		TermTaxonomy taxonomy = new TermTaxonomy(term, TaxonomyType.category);
		return taxonomy;
	}

	public TermTaxonomy createNicheTags(String niche) {
		Term term = new Term(niche, niche);
		TermTaxonomy taxonomy = new TermTaxonomy(term, TaxonomyType.post_tag);
		return taxonomy;
	}

}
