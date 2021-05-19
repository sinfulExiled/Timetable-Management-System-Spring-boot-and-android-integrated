package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Rooms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column(name = "number")
	private String number;
	
	@Column(name = "floor")
	private String floor;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "reserved_date")
	private String reservedDate;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "booked_teacher")
	private String bookedTeacher;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "min_capacity")
	private String minCapacity;
	
	@Column(name = "availability")
	private String availability;


	
	
	public Rooms(String number, String floor, String status, String reservedDate, String startTime, String endTime,
			String bookedTeacher, String maxCapacity, String minCapacity, String availability) {
		super();
		this.number = number;
		this.floor = floor;
		this.status = status;
		this.reservedDate = reservedDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bookedTeacher = bookedTeacher;
		this.maxCapacity = maxCapacity;
		this.minCapacity = minCapacity;
		this.availability = availability;
	}


	public Rooms() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getMinCapacity() {
		return minCapacity;
	}

	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBookedTeacher() {
		return bookedTeacher;
	}


	public void setBookedTeacher(String bookedTeacher) {
		this.bookedTeacher = bookedTeacher;
	}


	public String getReservedDate() {
		return reservedDate;
	}


	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}

