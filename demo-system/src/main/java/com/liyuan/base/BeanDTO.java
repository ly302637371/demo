package com.liyuan.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BeanDTO implements Serializable, Cloneable {
	private static final long serialVersionUID = -877305523L;
	protected String createdBy;
	protected Date creationDate;
	protected Date lastUpdateDate;
	protected String lastUpdatedBy;
	protected String lastUpdateIp;
	protected Long version;

	public BeanDTO() {
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public String getLastUpdateIp() {
		return this.lastUpdateIp;
	}

	public abstract String getLogFormName();

	public abstract String getLogTitle();

	public Long getVersion() {
		return this.version;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdateIp(String lastUpdateIp) {
		this.lastUpdateIp = lastUpdateIp;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}