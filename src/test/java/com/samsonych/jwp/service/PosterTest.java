package com.samsonych.jwp.service;

import is.ida.lib.service.exception.ServiceException;

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.samsonych.jwp.AppJUnit4SpringContextTests;
import com.samsonych.jwp.TestUtil;
import com.samsonych.jwp.model.Post;
import com.samsonych.jwp.model.Taxonomy;
import com.samsonych.jwp.model.TaxonomyType;
import com.samsonych.jwp.util.ContentUtilTest;

public class PosterTest extends AppJUnit4SpringContextTests {

	@SuppressWarnings("unchecked")
	@Test
	public final void testCreateNicheCategory() throws ServiceException {
		getPoster().setNiche("debt-consolidation");
		String category = getPoster().getNiche();

		String hqlQuery = String.format("from Taxonomy where term.name='%s'",
				category);

		if (getBaseDBManager().count("select count(*) " + hqlQuery) == 0) {
			getPoster().addNicheCategory();
		}
		Taxonomy taxonomy = getPoster().getWpManager().getTaxonomyCategory(
				category);
		Assert.assertNotNull(taxonomy);
		Assert.assertEquals("Debt-Consolidation", taxonomy.getTerm().getName());
		Assert.assertEquals("debt-consolidation", taxonomy.getTerm().getSlug());

		getBaseDBManager().delete(taxonomy);
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testCreateNicheTags() throws ServiceException {
		getPoster().setNiche("debt-relief-consolidation");
		getPoster()
				.addNicheTags("loan", "debt-relief-consolidation",
						"7 Credit Cards?",
						"Credits: Card, Company - Credit daily consolidating credited!");

		String[] tagsArr = new String[] { "Debt", "Relief", "Consolidation",
				"Loan", "Credit", "Card", "Company" };

		String hqlQuery = String.format(WPManager.HQL_TAXONOMY,
				StringUtils.join(tagsArr, "','"), TaxonomyType.post_tag);

		int tagsCounts = getBaseDBManager()
				.count("select count(*) " + hqlQuery);
		Assert.assertEquals(tagsArr.length, tagsCounts);

		List<Taxonomy> tags = getBaseDBManager().executeHQLQuery(hqlQuery);
		log.debug(tags);
		getBaseDBManager().deleteAll(tags);
	}

	@Test
	@Ignore
	public final void testGetTimeNextPublication() throws ServiceException {
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testGetPostFromFile() throws ServiceException {
		getPoster().setNiche("credit");
		getPoster().addNicheCategory();

		Post post = getPoster().getPostFromFile(TestUtil.getGadsTestFile());
		post = (Post) getBaseDBManager().saveOrUpdate(post);
		Assert.assertEquals(ContentUtilTest.EXPECTED_POST_NAME,
				post.getPostName());
		Assert.assertEquals(ContentUtilTest.EXPECTED_TITLE, post.getPostTitle());
		// getBaseDBManager().deleteAll(post.getTaxonomies());
		// getBaseDBManager().delete(post);
	}
}
