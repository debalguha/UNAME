package com.va.uma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "application", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Application implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5803631085624275364L;
	@Id
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Application() {

	}

	public Application(String name) {
		this.name = name;
	}
	// @ManyToMany(targetEntity = Access.class, cascade = { CascadeType.MERGE,
	// CascadeType.PERSIST })
	// @JoinTable(name = "app_access", joinColumns = { @JoinColumn(name =
	// "app_name") }, inverseJoinColumns = { @JoinColumn(name = "access_id") })
	// private Set<Access> accesses;
	//
	// public Set<Access> getAccesses() {
	// return accesses;
	// }
	//
	// public void setAccesses(Set<Access> accesses) {
	// this.accesses = accesses;
	// }

}