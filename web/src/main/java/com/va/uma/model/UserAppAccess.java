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
@Table(name = "user_app_access")
@Embeddable
public class UserAppAccess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 80)
	private String id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;

	@Column(name = "app_name", nullable = false, length = 100)
	private String appName;

	@OneToOne
	@JoinColumn(name = "access_id")
	private Access access;

	public UserAppAccess() {
		this.id = UUID.randomUUID().toString();
	}

	public UserAppAccess(UserInfo userInfo, String appName, Access access) {
		this.id = UUID.randomUUID().toString();
		this.userInfo = userInfo;
		this.appName = appName;
		this.access = access;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}