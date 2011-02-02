package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;
import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.util.ContentUtilTest;

public class PosterTest extends AppJUnit4SpringContextTests {

    @SuppressWarnings("unchecked")
    @Test
    public final void testCreateNicheCategory() throws ServiceException {

        Poster poster = new Poster("debt-consolidation");
        String category = poster.getNiche();

        String hqlQuery = String.format("from Taxonomy where term.name='%s'", category);

        if (baseDBManager.count("select count(*) " + hqlQuery) == 0) {
            poster.addNicheCategory();
        }
        Taxonomy taxonomy = Poster.getWpManager().getTaxonomyCategory(category);
        Assert.assertNotNull(taxonomy);
        Assert.assertEquals("Debt-Consolidation", taxonomy.getTerm().getName());
        Assert.assertEquals("debt-consolidation", taxonomy.getTerm().getSlug());

        baseDBManager.delete(taxonomy);
    }

    @SuppressWarnings("unchecked")
    @Test
    public final void testCreateNicheTags() throws ServiceException {
        Poster poster = new Poster("debt-relief-consolidation");
        poster.addNicheTags("loan", "debt-relief-consolidation", "7 Credit Card?",
                "Credit: Card, Company - Credit!");
        String hqlQuery = "from Taxonomy "
                + "where term.name in ('Debt','Relief','Consolidation','Loan','Credit','Card','Company')";

        int tagsCounts = baseDBManager.count("select count(*) " + hqlQuery);
        Assert.assertEquals(7, tagsCounts);

        List<Taxonomy> tags = baseDBManager.executeHQLQuery(hqlQuery);
        baseDBManager.deleteAll(tags);
    }

    @Test
    @Ignore
    public final void testGetTimeNextPublication() throws ServiceException {
    }

    @SuppressWarnings("unchecked")
    @Test
    public final void testGetPostFromFile() throws ServiceException {
        Poster poster = new Poster("credit");
        Post post = poster.getPostFromFile(testFile);
        Taxonomy category = poster.addNicheCategory();
        post.getTaxonomies().add(category);
        post = (Post) baseDBManager.saveOrUpdate(post);
        Assert.assertEquals(ContentUtilTest.EXPECTED_POST_NAME, post.getPostName());
        Assert.assertEquals(ContentUtilTest.EXPECTED_TITLE, post.getPostTitle());
    }

}
