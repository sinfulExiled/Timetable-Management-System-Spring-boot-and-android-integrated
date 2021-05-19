package com.cb007727.EEATimeTableManager.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Classes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column(name = "date")
	private String date;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "endtime")
	private String endTime;
	
	@Column(name = "venue")
	private String venue;

	@Column(name = "lecturer_name")
	private String lecturerName;
	
	@Column(name = "batch")
	private String batch;
	
	@Column(name = "module")
	private String module;
	
	@Column(name = "degree_type")
	private String degreeType;
	
	

	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classes(String date, String day, String time, String endtime, String venue, String lecturerName, String batch, String module,
			String degreeType) {
		super();
		this.date = date;
		this.day = day;
		this.time = time;
		this.endTime = endtime;
		this.venue = venue;
		this.lecturerName = lecturerName;
		this.batch = batch;
		this.module = module;
		this.degreeType = degreeType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
