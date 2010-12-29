/**
 *
 */
package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import org.apache.log4j.Logger;

import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.User;
import com.samsonych.service.dba.BaseDBManagerImpl;
import com.samsonych.service.dba.DBManagerFactory;

/**
 * @author samsonych
 * 
 */
public class WPManager {

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

    public Taxonomy saveOrUpdateTaxonomy(final Taxonomy taxonomy) throws ServiceException {
        return (Taxonomy) taxonomyManager.saveOrUpdate(taxonomy);
    }

    public User getUserById(final Long id) throws ServiceException {
        return (User) userManager.getById(id);
    }

}
