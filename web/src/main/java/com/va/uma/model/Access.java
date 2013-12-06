package com.va.uma.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_def")
public class Access implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 80)
	private String id;
	@Column(name = "access_name", unique = true, nullable = false, length = 100)
	private String name;
	@Column(name = "access_code", unique = true, nullable = false, length = 10)
	private String code;

	// @ManyToMany(targetEntity = Application.class, cascade = {
	// CascadeType.MERGE, CascadeType.PERSIST })
	// @JoinTable(name = "app_access", joinColumns = { @JoinColumn(name =
	// "access_id") }, inverseJoinColumns = { @JoinColumn(name = "app_name") })
	// private Set<Application> applications;

	public Access() {
		this.id = UUID.randomUUID().toString();
	}

	public Access(String name, String code) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Access [code=" + code + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}