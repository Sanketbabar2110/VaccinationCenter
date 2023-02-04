package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VaccinationCenter")
public class VaccinationCenter {
	
	@Id
	@Column(name = "VaccinationCenterId")
	private int id;

	@Column(name = "VaccinationCenterName")
	private String centerName;

	@Column(name = "VaccinationCenterAddress")
	private String centerAddress;

	public VaccinationCenter() {
		super();
	}

	public VaccinationCenter(int id, String centerName, String centerAddress) {
		super();
		this.id = id;
		this.centerName = centerName;
		this.centerAddress = centerAddress;
	}

	@Override
	public String toString() {
		return "VaccinationCenter [id=" + id + ", centerName=" + centerName + ", centerAddress=" + centerAddress + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterAddress() {
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

}
