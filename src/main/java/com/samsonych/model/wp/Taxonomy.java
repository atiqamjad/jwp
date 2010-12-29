package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;

/**
 * This table describes the taxonomy (category, link, or tag).
 */
@Entity
@Table(name = "wp_term_taxonomy", uniqueConstraints = @UniqueConstraint(columnNames = { "term_id",
        "taxonomy" }))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Taxonomy implements java.io.Serializable {

    private static final long serialVersionUID = -8899015337345756307L;
    private Long termTaxonomyId;
    private Term term;
    private TaxonomyType taxonomyType;
    private String description;
    private long parent;
    private long count;

    public Taxonomy() {
    }

    public Taxonomy(final Term term, final TaxonomyType taxonomyType) {
        this.term = term;
        this.taxonomyType = taxonomyType;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "term_taxonomy_id", unique = true, nullable = false)
    public Long getTermTaxonomyId() {
        return this.termTaxonomyId;
    }

    public void setTermTaxonomyId(final Long termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "term_id", nullable = false)
    public Term getTerm() {
        return this.term;
    }

    public void setTerm(final Term term) {
        this.term = term;
    }

    @Column(name = "taxonomy", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    public TaxonomyType getTaxonomyType() {
        return this.taxonomyType;
    }

    public void setTaxonomyType(final TaxonomyType taxonomy) {
        this.taxonomyType = taxonomy;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "parent", nullable = false)
    public long getParent() {
        return this.parent;
    }

    public void setParent(final long parent) {
        this.parent = parent;
    }

    @Column(name = "count", nullable = false)
    public long getCount() {
        return this.count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("Taxonomy [%s, %s]", term, taxonomyType);
    }

    public static Taxonomy createCategory(final String category, final String slug,
            final String desc) {
        Term term = new Term(category, StringUtils.capitalize(category));
        Taxonomy taxonomy = new Taxonomy(term, TaxonomyType.category);
        taxonomy.setDescription(desc);
        return taxonomy;
    }

    public static Taxonomy createTag(final String tag, final String slug, final String desc) {
        Term term = new Term(tag, slug);
        Taxonomy taxonomy = new Taxonomy(term, TaxonomyType.post_tag);
        taxonomy.setDescription(desc);
        return taxonomy;
    }

}
