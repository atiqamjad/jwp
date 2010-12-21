package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The core of the WordPress data.
 */
@Entity
@Table(name = "wp_posts")
public class WpPost implements java.io.Serializable {

	private static final long serialVersionUID = -3853310933943629341L;
	private Long id;
	private long postAuthor;
	private Date postDate;
	private Date postDateGmt;
	private String postContent;
	private String postTitle;
	private String postExcerpt;
	private String postStatus;
	private String commentStatus;
	private String pingStatus;
	private String postPassword;
	private String postName;
	private String toPing;
	private String pinged;
	private Date postModified;
	private Date postModifiedGmt;
	private String postContentFiltered;
	private long postParent;
	private String guid;
	private int menuOrder;
	private String postType;
	private String postMimeType;
	private long commentCount;

	public WpPost() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "post_author", nullable = false)
	public long getPostAuthor() {
		return this.postAuthor;
	}

	public void setPostAuthor(long postAuthor) {
		this.postAuthor = postAuthor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date", nullable = false, length = 19)
	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date_gmt", nullable = false, length = 19)
	public Date getPostDateGmt() {
		return this.postDateGmt;
	}

	public void setPostDateGmt(Date postDateGmt) {
		this.postDateGmt = postDateGmt;
	}

	@Column(name = "post_content", nullable = false)
	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Column(name = "post_title", nullable = false, length = 65535)
	public String getPostTitle() {
		return this.postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	@Column(name = "post_excerpt", nullable = false, length = 65535)
	public String getPostExcerpt() {
		return this.postExcerpt;
	}

	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt;
	}

	@Column(name = "post_status", nullable = false, length = 20)
	public String getPostStatus() {
		return this.postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	@Column(name = "comment_status", nullable = false, length = 20)
	public String getCommentStatus() {
		return this.commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	@Column(name = "ping_status", nullable = false, length = 20)
	public String getPingStatus() {
		return this.pingStatus;
	}

	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}

	@Column(name = "post_password", nullable = false, length = 20)
	public String getPostPassword() {
		return this.postPassword;
	}

	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}

	@Column(name = "post_name", nullable = false, length = 200)
	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Column(name = "to_ping", nullable = false, length = 65535)
	public String getToPing() {
		return this.toPing;
	}

	public void setToPing(String toPing) {
		this.toPing = toPing;
	}

	@Column(name = "pinged", nullable = false, length = 65535)
	public String getPinged() {
		return this.pinged;
	}

	public void setPinged(String pinged) {
		this.pinged = pinged;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_modified", nullable = false, length = 19)
	public Date getPostModified() {
		return this.postModified;
	}

	public void setPostModified(Date postModified) {
		this.postModified = postModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_modified_gmt", nullable = false, length = 19)
	public Date getPostModifiedGmt() {
		return this.postModifiedGmt;
	}

	public void setPostModifiedGmt(Date postModifiedGmt) {
		this.postModifiedGmt = postModifiedGmt;
	}

	@Column(name = "post_content_filtered", nullable = false, length = 65535)
	public String getPostContentFiltered() {
		return this.postContentFiltered;
	}

	public void setPostContentFiltered(String postContentFiltered) {
		this.postContentFiltered = postContentFiltered;
	}

	@Column(name = "post_parent", nullable = false)
	public long getPostParent() {
		return this.postParent;
	}

	public void setPostParent(long postParent) {
		this.postParent = postParent;
	}

	@Column(name = "guid", nullable = false)
	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Column(name = "menu_order", nullable = false)
	public int getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "post_type", nullable = false, length = 20)
	public String getPostType() {
		return this.postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	@Column(name = "post_mime_type", nullable = false, length = 100)
	public String getPostMimeType() {
		return this.postMimeType;
	}

	public void setPostMimeType(String postMimeType) {
		this.postMimeType = postMimeType;
	}

	@Column(name = "comment_count", nullable = false)
	public long getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

}
