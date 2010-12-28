package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The core of the WordPress data.
 */
@Entity
@Table(name = "wp_posts")
public class Post implements java.io.Serializable {

	private static final long serialVersionUID = -3853310933943629341L;

	public static Post createDefaultPost() {
		Post post = new Post();
		post.setCommentStatus(CommentStatus.open);
		post.setPingStatus(PingStatus.open);
		post.setPostStatusEnum(PostStatus.PUBLISH);
		post.setPostPassword("");
		post.setToPing("");
		post.setPostDate(new Date());
		post.setPostDateGmt(new Date());
		post.setPostModified(new Date());
		post.setPostModifiedGmt(new Date());
		return post;
	}

	private long commentCount;
	private List<Comment> comments;
	private CommentStatus commentStatus;
	private String guid;
	private Long id;
	private int menuOrder;
	private String pinged;
	private PingStatus pingStatus;
	private User postAuthor;
	private String postContent;
	private String postContentFiltered;
	private Date postDate;
	private Date postDateGmt;
	private String postExcerpt;
	private List<PostMeta> postMetaItems;
	private String postMimeType;
	private Date postModified;
	private Date postModifiedGmt;
	private String postName;
	private long postParent;

	private String postPassword;

	private String postStatus;

	private String postTitle;

	private PostType postType;
	private Map<Integer, TermTaxonomy> termTaxonomies;
	private String toPing;

	public Post() {
	}

	@Column(name = "comment_count")
	public long getCommentCount() {
		return this.commentCount;
	}

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Comment> getComments() {
		return comments;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "comment_status", nullable = false, length = 20)
	public CommentStatus getCommentStatus() {
		return this.commentStatus;
	}

	@Column(name = "guid", nullable = false)
	public String getGuid() {
		return this.guid;
	}

	@Id
	@GeneratedValue
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	@Column(name = "menu_order")
	public int getMenuOrder() {
		return this.menuOrder;
	}

	// @Column(name = "pinged", nullable = false, length = 65535)
	@Lob
	@Column(name = "pinged", nullable = false)
	public String getPinged() {
		return this.pinged;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ping_status", nullable = false, length = 20)
	public PingStatus getPingStatus() {
		return this.pingStatus;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_author", nullable = false)
	public User getPostAuthor() {
		return this.postAuthor;
	}

	@Lob
	@Column(name = "post_content", nullable = false)
	public String getPostContent() {
		return this.postContent;
	}

	@Lob
	@Column(name = "post_content_filtered")
	public String getPostContentFiltered() {
		return this.postContentFiltered;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date", nullable = false)
	public Date getPostDate() {
		return this.postDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date_gmt", nullable = false)
	public Date getPostDateGmt() {
		return this.postDateGmt;
	}

	@Lob
	@Column(name = "post_excerpt")
	public String getPostExcerpt() {
		return this.postExcerpt;
	}

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PostMeta> getPostMetaItems() {
		return postMetaItems;
	}

	@Column(name = "post_mime_type", length = 100)
	public String getPostMimeType() {
		return this.postMimeType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_modified")
	public Date getPostModified() {
		return this.postModified;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_modified_gmt")
	public Date getPostModifiedGmt() {
		return this.postModifiedGmt;
	}

	@Column(name = "post_name", nullable = false, length = 200)
	public String getPostName() {
		return this.postName;
	}

	@Column(name = "post_parent")
	public long getPostParent() {
		return this.postParent;
	}

	@Column(name = "post_password", nullable = false, length = 20)
	public String getPostPassword() {
		return this.postPassword;
	}

	@Column(name = "post_status", nullable = false, length = 20)
	public String getPostStatus() {
		return this.postStatus;
	}

	@Transient
	public PostStatus getPostStatusEnum() {
		return PostStatus.valueOf(this.postStatus);
	}

	@Lob
	@Column(name = "post_title", nullable = false)
	// @Column(name = "post_title", nullable = false, length = 65535)
	public String getPostTitle() {
		return this.postTitle;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "post_type", nullable = false, length = 20)
	public PostType getPostType() {
		return this.postType;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "wp_term_relationships", joinColumns = @JoinColumn(name = "object_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "term_taxonomy_id", referencedColumnName = "term_taxonomy_id"))
	@MapKeyColumn(name = "term_order")
	public Map<Integer, TermTaxonomy> getTermTaxonomies() {
		return termTaxonomies;
	}

	// @Column(name = "to_ping", nullable = false, length = 65535)
	@Column(name = "to_ping")
	public String getToPing() {
		return this.toPing;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setCommentStatus(CommentStatus commentStatus) {
		this.commentStatus = commentStatus;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public void setPinged(String pinged) {
		this.pinged = pinged;
	}

	public void setPingStatus(PingStatus pingStatus) {
		this.pingStatus = pingStatus;
	}

	public void setPostAuthor(User postAuthor) {
		this.postAuthor = postAuthor;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public void setPostContentFiltered(String postContentFiltered) {
		this.postContentFiltered = postContentFiltered;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public void setPostDateGmt(Date postDateGmt) {
		this.postDateGmt = postDateGmt;
	}

	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt;
	}

	public void setPostMetaItems(List<PostMeta> postMetaItems) {
		this.postMetaItems = postMetaItems;
	}

	public void setPostMimeType(String postMimeType) {
		this.postMimeType = postMimeType;
	}

	public void setPostModified(Date postModified) {
		this.postModified = postModified;
	}

	public void setPostModifiedGmt(Date postModifiedGmt) {
		this.postModifiedGmt = postModifiedGmt;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public void setPostParent(long postParent) {
		this.postParent = postParent;
	}

	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	@Transient
	public void setPostStatusEnum(PostStatus postStatus) {
		this.postStatus = postStatus.getStatus();
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public void setTermTaxonomies(Map<Integer, TermTaxonomy> termTaxonomies) {
		this.termTaxonomies = termTaxonomies;
	}

	public void setToPing(String toPing) {
		this.toPing = toPing;
	}

}
