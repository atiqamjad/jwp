package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WpPostmeta generated by hbm2java
 */
@Entity
@Table(name = "wp_postmeta")
public class WpPostMeta implements java.io.Serializable {

	private static final long serialVersionUID = -7240950919239724499L;
	private Long metaId;
	private long postId;
	private String metaKey;
	private String metaValue;

	public WpPostMeta() {
	}

	public WpPostMeta(long postId) {
		this.postId = postId;
	}

	public WpPostMeta(long postId, String metaKey, String metaValue) {
		this.postId = postId;
		this.metaKey = metaKey;
		this.metaValue = metaValue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "meta_id", unique = true, nullable = false)
	public Long getMetaId() {
		return this.metaId;
	}

	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	@Column(name = "post_id", nullable = false)
	public long getPostId() {
		return this.postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Column(name = "meta_key")
	public String getMetaKey() {
		return this.metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	@Column(name = "meta_value")
	public String getMetaValue() {
		return this.metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

}