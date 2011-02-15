package com.samsonych.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.AppJUnit4SpringContextTests;
import com.samsonych.TestUtil;
import com.samsonych.model.wp.Post;
import com.samsonych.model.wp.Taxonomy;
import com.samsonych.model.wp.TaxonomyType;
import com.samsonych.util.ContentUtilTest;

public class PosterTest extends AppJUnit4SpringContextTests {

	private Poster poster;

	@Override
	public void initialize() throws ServiceException {
		super.initialize();
		poster = new Poster(grabber);
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testCreateNicheCategory() throws ServiceException {
		poster.setNiche("debt-consolidation");
		String category = poster.getNiche();

		String hqlQuery = String.format("from Taxonomy where term.name='%s'",
				category);

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
		poster.setNiche("debt-relief-consolidation");
		poster.addNicheTags("loan", "debt-relief-consolidation",
				"7 Credit Cards?",
				"Credits: Card, Company - Credit daily consolidating credited!");

		String[] tagsArr = new String[] { "Debt", "Relief", "Consolidation",
				"Loan", "Credit", "Card", "Company" };

		String hqlQuery = String.format(WPManager.HQL_TAXONOMY,
				StringUtils.join(tagsArr, "','"), TaxonomyType.post_tag);

		int tagsCounts = baseDBManager.count("select count(*) " + hqlQuery);
		Assert.assertEquals(tagsArr.length, tagsCounts);

		List<Taxonomy> tags = baseDBManager.executeHQLQuery(hqlQuery);
		log.debug(tags);
		baseDBManager.deleteAll(tags);
	}

	@Test
	@Ignore
	public final void testGetTimeNextPublication() throws ServiceException {
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testGetPostFromFile() throws ServiceException {
		poster.setNiche("credit");
		poster.addNicheCategory();

		Post post = poster.getPostFromFile(TestUtil.getTestFile());
		post = (Post) baseDBManager.saveOrUpdate(post);
		Assert.assertEquals(ContentUtilTest.EXPECTED_POST_NAME,
				post.getPostName());
		Assert.assertEquals(ContentUtilTest.EXPECTED_TITLE, post.getPostTitle());
		// baseDBManager.deleteAll(post.getTaxonomies());
		// baseDBManager.delete(post);
	}
}
