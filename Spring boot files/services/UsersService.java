package com.cb007727.EEATimeTableManager.services;

import java.util.List;

import com.cb007727.EEATimeTableManager.models.Users;

public interface UsersService {

	void saveUsers(Users users);
	List<Users> getallUsers();
	Users getUserByID(long id);
	Users findUserUsingEnhancedForLoop(String names,String password);
	
}	
