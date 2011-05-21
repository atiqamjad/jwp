/**
 * 
 */
package com.samsonych.jwp.service.dba;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * @author samsonych
 * 
 */
public class JWPNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 4119562019615502366L;
	private String tablePrefix;

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	@Override
	public String tableName(String tableName) {
		return getTablePrefix() + super.tableName(tableName);
	}

}
