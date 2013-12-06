package com.va.uma.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "app_access")
@Embeddable
public class AppAccess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 80)
	private String id;
	@Column(name = "app_name", nullable = false, length = 100)
	private String appName;

	@OneToOne
	@JoinColumn(name = "access_id")
	private Access access;

	public AppAccess() {
		this.id = UUID.randomUUID().toString();
	}

	public AppAccess(String appName, Access access) {
		this.id = UUID.randomUUID().toString();
		this.appName = appName;
		this.access = access;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "AppAccess [app_name=" + appName + ", access_id=" + access.getId() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

}