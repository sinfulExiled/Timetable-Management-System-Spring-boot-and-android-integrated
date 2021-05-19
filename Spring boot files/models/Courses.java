package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Courses {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "number_of_teachers")
	private String numberofTeachers;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "total_students")
	private String totalStudents;
	
	@Column(name = "course_code")
	private String courseCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getNumberofTeachers() {
		return numberofTeachers;
	}

	public void setNumberofTeachers(String numberofTeachers) {
		this.numberofTeachers = numberofTeachers;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(String totalStudents) {
		this.totalStudents = totalStudents;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	
}
