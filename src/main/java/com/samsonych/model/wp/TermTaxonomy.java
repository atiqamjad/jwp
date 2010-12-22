package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * This table describes the taxonomy (category, link, or tag).
 */
@Entity
@Table(name = "wp_term_taxonomy", uniqueConstraints = @UniqueConstraint(columnNames = {
		"term_id", "taxonomy" }))
public class TermTaxonomy implements java.io.Serializable {

	private static final long serialVersionUID = -8899015337345756307L;
	private Long termTaxonomyId;
	private long termId;
	private String taxonomy;
	private String description;
	private long parent;
	private long count;

	public TermTaxonomy() {
	}

	public TermTaxonomy(long termId, String taxonomy, String description,
			long parent, long count) {
		this.termId = termId;
		this.taxonomy = taxonomy;
		this.description = description;
		this.parent = parent;
		this.count = count;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "term_taxonomy_id", unique = true, nullable = false)
	public Long getTermTaxonomyId() {
		return this.termTaxonomyId;
	}

	public void setTermTaxonomyId(Long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	@Column(name = "term_id", nullable = false)
	public long getTermId() {
		return this.termId;
	}

	public void setTermId(long termId) {
		this.termId = termId;
	}

	@Column(name = "taxonomy", nullable = false, length = 32)
	public String getTaxonomy() {
		return this.taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent", nullable = false)
	public long getParent() {
		return this.parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	@Column(name = "count", nullable = false)
	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
