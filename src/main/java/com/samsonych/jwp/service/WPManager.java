/**
 *
 */
package com.samsonych.jwp.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.samsonych.jwp.model.Post;
import com.samsonych.jwp.model.Taxonomy;
import com.samsonych.jwp.model.TaxonomyType;
import com.samsonych.jwp.model.User;
import com.samsonych.jwp.service.dba.BaseDBManagerImpl;
import com.samsonych.jwp.service.dba.DBManagerFactory;

/**
 * @author samsonych
 *
 */
public class WPManager {

    static final String HQL_TAXONOMY = "from Taxonomy t where t.term.name in ('%s') and t.taxonomyType='%s'";

    private static final Logger LOG = Logger.getLogger(WPManager.class);

    private static BaseDBManagerImpl postDBM = DBManagerFactory.getBaseDBManager(Post.class);
    private static BaseDBManagerImpl userDBM = DBManagerFactory.getBaseDBManager(User.class);
    private static BaseDBManagerImpl taxonomyDBM = DBManagerFactory
            .getBaseDBManager(Taxonomy.class);

    public Post saveOrUpdatePost(final Post post) throws ServiceException {
        return (Post) postDBM.saveOrUpdate(post);
    }

    public Taxonomy saveOrUpdateTaxonomy(final Taxonomy taxonomy) throws ServiceException {
        return (Taxonomy) taxonomyDBM.saveOrUpdate(taxonomy);
    }

    public User getUserById(final Long id) throws ServiceException {
        return (User) userDBM.getById(id);
    }

    public List<Taxonomy> saveOrUpdateTaxonomyAll(final List<Taxonomy> taxonomies)
            throws ServiceException {
        return taxonomyDBM.saveOrUpdateAll(taxonomies);
    }

    public Taxonomy getTaxonomyTag(final String tag) throws ServiceException {
        return getTaxonomy(tag, TaxonomyType.post_tag);
    }

    public Taxonomy getTaxonomyCategory(final String category) throws ServiceException {
        return getTaxonomy(category, TaxonomyType.category);
    }

    public Taxonomy getTaxonomy(final String taxonomy, final TaxonomyType type)
            throws ServiceException {
        String hqlQuery = String.format(HQL_TAXONOMY, taxonomy, type);
        List<Taxonomy> taxonomies = taxonomyDBM.executeHQLQuery(hqlQuery);
        return taxonomies.equals(Collections.EMPTY_LIST) ? null : taxonomies.get(0);
    }

    // FIXME
    public int deleteTaxonomies(final String... tags) throws ServiceException {
        String hqlQuery = String.format("delete " + HQL_TAXONOMY, StringUtils.join(tags, "','"),
                TaxonomyType.post_tag);
        // TODO executeUpdateHQL
        taxonomyDBM.executeHQLQuery(hqlQuery);
        return 1;
    }

}
