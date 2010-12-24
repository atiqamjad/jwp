package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The categories for both posts and links and the tags for posts.
 */
@Entity
@Table(name = "wp_terms", uniqueConstraints = @UniqueConstraint(columnNames = "slug"))
public class Term implements java.io.Serializable {

    private static final long serialVersionUID = 4860621151071891850L;
    private Long termId;
    private String name;
    private String slug;
    private long termGroup;
    private TermTaxonomy termTaxonomy;

    @OneToOne(optional = false, mappedBy = "term", fetch = FetchType.LAZY)
    // @PrimaryKeyJoinColumn
    public TermTaxonomy getTermTaxonomy() {
        return termTaxonomy;
    }

    public void setTermTaxonomy(TermTaxonomy taxonomy) {
        this.termTaxonomy = taxonomy;
    }

    public Term() {
    }

    public Term(String name, String slug, long termGroup) {
        this.name = name;
        this.slug = slug;
        this.termGroup = termGroup;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "term_id", unique = true, nullable = false)
    public Long getTermId() {
        return this.termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "slug", unique = true, nullable = false, length = 200)
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name = "term_group", nullable = false)
    public long getTermGroup() {
        return this.termGroup;
    }

    public void setTermGroup(long termGroup) {
        this.termGroup = termGroup;
    }

	@Override
	public String toString() {
		return String.format("Term [name=%s]", name);
	}

}
