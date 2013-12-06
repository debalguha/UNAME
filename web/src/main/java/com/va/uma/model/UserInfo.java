package com.va.uma.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_info", uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class UserInfo implements java.io.Serializable {

	public enum UserStatus {
		active, inactive
	}

	public enum UserType {
		user, creator
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650114334774359089L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 100)
	private String id;
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;
	@Column(name = "password", nullable = false, length = 80)
	private String password;
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	@Column(name = "type", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private UserType type;
	@Column(name = "phone", nullable = true, length = 30)
	private String phone;
	@Column(name = "email", nullable = true, length = 50)
	private String email;
	@Column(name = "first_name", nullable = true, length = 50)
	private String firstName;
	@Column(name = "last_name", nullable = true, length = 50)
	private String lastName;
	@OneToOne
	@JoinColumn(name = "team_id")
	private Team team;
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	@Column(name = "approver_fullname", nullable = true, length = 100)
	private String approverFullname;
	@Column(name = "approver_email", nullable = true, length = 50)
	private String approverEmail;
	@Column(name = "approver_phone", nullable = true, length = 30)
	private String approverPhone;
	@Column(name = "request_detail", nullable = true, length = 1000)
	private String requestDetail;

	@Transient
	private List<UserAppAccess> userAppAccessList;

	public UserInfo() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getApproverFullname() {
		return approverFullname;
	}

	public void setApproverFullname(String approverFullname) {
		this.approverFullname = approverFullname;
	}

	public String getApproverEmail() {
		return approverEmail;
	}

	public void setApproverEmail(String approverEmail) {
		this.approverEmail = approverEmail;
	}

	public String getApproverPhone() {
		return approverPhone;
	}

	public void setApproverPhone(String approverPhone) {
		this.approverPhone = approverPhone;
	}

	public String getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<UserAppAccess> getUserAppAccessList() {
		return userAppAccessList;
	}

	public void setUserAppAccessList(List<UserAppAccess> userAppAccessList) {
		this.userAppAccessList = userAppAccessList;
	}

}