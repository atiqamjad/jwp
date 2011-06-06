/**
 *
 */
package com.samsonych.jwp.model;

/**
 * @author samsonov
 *
 */
public enum PostStatus {
	/** . */
	PUBLISH("publish"),
	/** . */
	PENDING("pending"),
	/** . */
	DRAFT("draft"),
	/** . */
	AUTO_DRAFT("auto-draft"),
	/** . */
	PRIVATE("private"),
	/** . */
	STATIC("static"),
	/** . */
	OBJECT("object"),
	/** . */
	ATTACHMENT("attachment"),
	/** . */
	INHERIT("inherit"),
	/** . */
	FUTURE("future");

	private String status;

	PostStatus(String value) {
		this.status = value;
	}

	public static PostStatus fromValue(final String status) {
		for (PostStatus s : PostStatus.values()) {
			if (s.status.equals(status)) {
				return s;
			}
		}
		throw new IllegalArgumentException(String.valueOf(status));
	}

	public String getStatus() {
		return status;
	}
}
