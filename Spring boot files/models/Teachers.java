package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teachers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "staff_id")
	private String StaffID;
	
	@Column(name = "emailaddress")
	private String EmailAddress;
	
	@Column(name = "teaching_model")
	private String TeachingModel;
	
	@Column(name = "password")
	private String Password;
	
	@Column(name = "DOB")
	private String DOB;
	
	@Column(name = "number")
	private String Number;
	
	@Column(name = "gender")
	private String Gender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStaffID() {
		return StaffID;
	}

	public void setStaffID(String staffID) {
		StaffID = staffID;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getTeachingModel() {
		return TeachingModel;
	}

	public void setTeachingModel(String teachingModel) {
		TeachingModel = teachingModel;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	
	
	
	      
}
