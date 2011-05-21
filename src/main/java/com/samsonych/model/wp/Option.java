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
 * The Options set under the Administration > Settings panel.
 */
@Entity
@Table(name = "options", uniqueConstraints = @UniqueConstraint(columnNames = "option_name"))
public class Option implements java.io.Serializable {

	private static final long serialVersionUID = 9143025689128920908L;
	private Long optionId;
	private int blogId;
	private String optionName;
	private String optionValue;
	private String autoload;

	public Option() {
	}

	public Option(int blogId, String optionName, String optionValue,
			String autoload) {
		this.blogId = blogId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.autoload = autoload;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "option_id", unique = true, nullable = false)
	public Long getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	@Column(name = "blog_id", nullable = false)
	public int getBlogId() {
		return this.blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	@Column(name = "option_name", unique = true, nullable = false, length = 64)
	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Column(name = "option_value", nullable = false)
	public String getOptionValue() {
		return this.optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	@Column(name = "autoload", nullable = false, length = 20)
	public String getAutoload() {
		return this.autoload;
	}

	public void setAutoload(String autoload) {
		this.autoload = autoload;
	}

}
