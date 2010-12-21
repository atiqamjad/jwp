package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WpTermRelationshipsId generated by hbm2java
 */
@Embeddable
public class WpTermRelationshipId implements java.io.Serializable {

	private static final long serialVersionUID = -5390476229672148208L;
	private long objectId;
	private long termTaxonomyId;

	public WpTermRelationshipId() {
	}

	public WpTermRelationshipId(long objectId, long termTaxonomyId) {
		this.objectId = objectId;
		this.termTaxonomyId = termTaxonomyId;
	}

	@Column(name = "object_id", nullable = false)
	public long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	@Column(name = "term_taxonomy_id", nullable = false)
	public long getTermTaxonomyId() {
		return this.termTaxonomyId;
	}

	public void setTermTaxonomyId(long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WpTermRelationshipId))
			return false;
		WpTermRelationshipId castOther = (WpTermRelationshipId) other;

		return (this.getObjectId() == castOther.getObjectId())
				&& (this.getTermTaxonomyId() == castOther.getTermTaxonomyId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getObjectId();
		result = 37 * result + (int) this.getTermTaxonomyId();
		return result;
	}

}