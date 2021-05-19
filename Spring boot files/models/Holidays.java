package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holidays")
public class Holidays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "holiday_date")
	private String holidayDate;
	

	@Column(name = "holiday_name")
	private String holidayName;

	@Column(name = "teacher_requested")
	private String teacherRequested;
	
	public Holidays() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Holidays(String holidayDate, String holidayName, String teacherRequested) {
		super();
		this.holidayDate = holidayDate;
		this.holidayName = holidayName;
		this.teacherRequested = teacherRequested;
	}

	
	


	public String getTeacherRequested() {
		return teacherRequested;
	}



	public void setTeacherRequested(String teacherRequested) {
		this.teacherRequested = teacherRequested;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getHolidayDate() {
		return holidayDate;
	}


	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}


	public String getHolidayName() {
		return holidayName;
	}


	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
	

}
