package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;
import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.User;

public class PosterTest extends AppJUnit4SpringContextTests {

    @SuppressWarnings("unchecked")
    @Test
    public final void testGetPostFromFile() throws ServiceException {
        Poster poster = new Poster("credit");
        Post post = poster.getPostFromFile(testFile);
        User author = (User) baseDBManager.setPersistentClass(User.class).getById(1L);
        post.setPostAuthor(author);
        post.setPostName(testFile.getName());
        baseDBManager.saveOrUpdate(post);
    }

    @SuppressWarnings("unchecked")
    @Test
    public final void testCreateNicheCategory() throws ServiceException {

        Poster poster = new Poster("debt-consolidation");
        String category = poster.getNiche();

        String hqlQuery = String.format("from Taxonomy where term.name='%s'", category);

        if (baseDBManager.count("select count(*) " + hqlQuery) == 0) {
            poster.addNicheCategory();
        }
        Taxonomy taxonomy = (Taxonomy) baseDBManager.executeHQLQuery(hqlQuery).get(0);
        Assert.assertNotNull(taxonomy);
        Assert.assertEquals("debt-consolidation", taxonomy.getTerm().getName());
        Assert.assertEquals("Debt-Consolidation", taxonomy.getTerm().getSlug());

        baseDBManager.delete(taxonomy);
    }

    @Test
    public final void testCreateNicheTags() throws ServiceException {
        Poster poster = new Poster("debt-relief-consolidation");
        poster.addNicheTags("loan", "debt-relief-consolidation", "Credit Card",
                "Credit: Card, Company - Credit");
        String hqlQuery = "from Taxonomy "
                + "where term.name in ('debt','relief','consolidation','loan','credit','card','company')";
        Assert.assertEquals(7, baseDBManager.count("select count(*) " + hqlQuery));

        List<Taxonomy> tags = baseDBManager.executeHQLQuery(hqlQuery);
//        baseDBManager.deleteAll(tags);
    }
}
