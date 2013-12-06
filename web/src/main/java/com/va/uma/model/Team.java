package com.va.uma.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "team", uniqueConstraints = { @UniqueConstraint(columnNames = "team_name"), @UniqueConstraint(columnNames = "team_code") })
public class Team implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 80)
	private String id;
	@Column(name = "team_name", unique = true, nullable = false, length = 100)
	private String name;
	@Column(name = "team_code", unique = true, nullable = false, length = 10)
	private String code;

	public Team() {
		this.id = UUID.randomUUID().toString();
	}

	public Team(String name, String code) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Team [code=" + code + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}