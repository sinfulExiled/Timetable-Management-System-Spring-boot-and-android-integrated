package com.cb007727.EEATimeTableManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "roles")
    private String roles;
	
	
	

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Users(String emailAddress, String password, String role) {
		super();
		this.userName = emailAddress;
		this.password = password;
		this.roles = role;
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	



	public String getRoles() {
		return roles;
	}



	public void setRoles(String roles) {
		this.roles = roles;
	}


	
	
	

	

	
	
}

