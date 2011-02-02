/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.TaxonomyType;
import com.samsonych.model.wp.User;
import com.samsonych.service.dba.BaseDBManagerImpl;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author samsonych
 *
 */
public class WPManager {

	static final String HQL_TAXONOMY = "from Taxonomy t where t.term.name in ('%s') and t.taxonomyType='%s'";

	private static Logger LOG = Logger.getLogger(WPManager.class);

	private static BaseDBManagerImpl postManager;
	private static BaseDBManagerImpl userManager;
	private static BaseDBManagerImpl taxonomyManager;

	public WPManager() {
		postManager = DBManagerFactory.getBaseDBManager(Post.class);
		userManager = DBManagerFactory.getBaseDBManager(User.class);
		taxonomyManager = DBManagerFactory.getBaseDBManager(Taxonomy.class);
	}

	public Post saveOrUpdatePost(final Post post) throws ServiceException {
		return (Post) postManager.saveOrUpdate(post);
	}

	public Taxonomy saveOrUpdateTaxonomy(final Taxonomy taxonomy)
			throws ServiceException {
		return (Taxonomy) taxonomyManager.saveOrUpdate(taxonomy);
	}

	public User getUserById(final Long id) throws ServiceException {
		return (User) userManager.getById(id);
	}

	public List<Taxonomy> saveOrUpdateTaxonomyAll(
			final List<Taxonomy> taxonomies) throws ServiceException {
		return taxonomyManager.saveOrUpdateAll(taxonomies);
	}

	public Taxonomy getTaxonomyTag(final String tag) throws ServiceException {
		return getTaxonomy(tag, TaxonomyType.post_tag);
	}

	public Taxonomy getTaxonomyCategory(final String category)
			throws ServiceException {
		return getTaxonomy(category, TaxonomyType.category);
	}

	public Taxonomy getTaxonomy(final String taxonomy, final TaxonomyType type)
			throws ServiceException {
		String hqlQuery = String.format(HQL_TAXONOMY, taxonomy, type);
		List<Taxonomy> taxonomies = taxonomyManager.executeHQLQuery(hqlQuery);
		return taxonomies.equals(Collections.EMPTY_LIST) ? null : taxonomies
				.get(0);
	}

	public int deleteTaxonomies(final String... tags) throws ServiceException {
		String hqlQuery = String.format("delete " + HQL_TAXONOMY,
				StringUtils.join(tags, "','"), TaxonomyType.post_tag);
		//TODO executeUpdateHQL
		taxonomyManager.executeHQLQuery(hqlQuery);
		return 1;
	}

}
