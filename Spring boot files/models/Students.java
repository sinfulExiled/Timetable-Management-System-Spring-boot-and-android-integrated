package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "studentRef")
	private String StudentRef;
	
	@Column(name = "emailaddress")
	private String EmailAddress;
	
	@Column(name = "courseStudy")
	private String CourseStudy;
	
	@Column(name = "password")
	private String Password;
	
	@Column(name = "DOB")
	private String DOB;
	
	@Column(name = "number")
	private String Number;
	
	@Column(name = "gender")
	private String Gender;

	
	
	public Students() {
		super();
	}

	public Students(String userName, String studentRef, String emailAddress, String courseStudy, String password,
			String dOB, String number, String gender) {
		super();
		this.userName = userName;
		StudentRef = studentRef;
		EmailAddress = emailAddress;
		CourseStudy = courseStudy;
		Password = password;
		DOB = dOB;
		Number = number;
		Gender = gender;
	}

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

	public String getStudentRef() {
		return StudentRef;
	}

	public void setStudentRef(String studentRef) {
		StudentRef = studentRef;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getCourseStudy() {
		return CourseStudy;
	}

	public void setCourseStudy(String courseStudy) {
		CourseStudy = courseStudy;
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
