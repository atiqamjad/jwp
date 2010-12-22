package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Posts are associated with categories and tags from the wp_terms table and
 * this association.
 */
@Entity
@Table(name = "wp_term_relationships")
public class TermRelationship implements java.io.Serializable {

	private static final long serialVersionUID = 9159480990706478868L;
	private TermRelationshipId id;
	private int termOrder;

	public TermRelationship() {
	}

	public TermRelationship(TermRelationshipId id, int termOrder) {
		this.id = id;
		this.termOrder = termOrder;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "objectId", column = @Column(name = "object_id", nullable = false)),
			@AttributeOverride(name = "termTaxonomyId", column = @Column(name = "term_taxonomy_id", nullable = false)) })
	public TermRelationshipId getId() {
		return this.id;
	}

	public void setId(TermRelationshipId id) {
		this.id = id;
	}

	@Column(name = "term_order", nullable = false)
	public int getTermOrder() {
		return this.termOrder;
	}

	public void setTermOrder(int termOrder) {
		this.termOrder = termOrder;
	}

}
