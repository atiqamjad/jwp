package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

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
        poster.addNicheCategory();

        Taxonomy taxonomy2 = (Taxonomy) baseDBManager.executeHQLQuery(
                String.format("from TermTaxonomy where term.name='%s'", category)).get(0);
        Assert.assertNotNull(taxonomy2);
        Assert.assertEquals(taxonomy2.getTerm().getName(), "debt-consolidation");
        Assert.assertEquals(taxonomy2.getTerm().getSlug(), "Debt-Consolidation");
        
        baseDBManager.deleteById(taxonomy2);
    }

    @Test
    @Ignore
    public final void testCreateNicheTags() throws ServiceException {
        Poster poster = new Poster("debt-relief");
        String category = poster.getNiche();
        baseDBManager.saveOrUpdateAll(poster.addNicheTags());

        Taxonomy taxonomy2 = (Taxonomy) baseDBManager.executeHQLQuery(
                String.format("from TermTaxonomy where term.name='%s'", category));
        Assert.assertNotNull(taxonomy2);
        Assert.assertEquals(taxonomy2.getTerm().getName(), "debt-consolidation");
        Assert.assertEquals(taxonomy2.getTerm().getSlug(), "Debt-Consolidation");
        
        baseDBManager.deleteById(taxonomy2);
    }

}
