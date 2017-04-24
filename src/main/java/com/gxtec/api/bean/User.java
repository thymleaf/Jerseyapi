package com.gxtec.api.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id 
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "unit_id")
	private String unitId;
	@Column(name = "password")
	private String password;
	@Column(name = "sort_no")
	private String sortNo;
	@Column(name = "enabled")
	private String enabled;
	@Column(name = "cert_no")
	private String certNo;
	@Column(name = "status")
	private String status;
	@Column(name = "tel")
	private String tel;
	@Column(name = "mobtel")
	private String mobtel;
	@Column(name = "other_tel")
	private String otherTel;
	@Column(name = "fax")
	private String fax;
	@Column(name = "email")
	private String email;
	@Column(name = "role")
	private String role;
	@Column(name = "duty")
	private String duty;
	@Column(name = "remark")
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobtel() {
		return mobtel;
	}

	public void setMobtel(String mobtel) {
		this.mobtel = mobtel;
	}

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
