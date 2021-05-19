package com.cb007727.EEATimeTableManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.repository.UsersRepository;

@Service
public class UsersServiceIMPL implements UsersService{

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public void saveUsers(Users users) {
		this.usersRepository.save(users);
	}

	@Override
	public List<Users> getallUsers() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Override
	public Users getUserByID(long id) {
		Optional<Users> userInstance = usersRepository.findById(id);
		Users users = null;
		if(userInstance.isPresent()) {
			users = userInstance.get();
			String username = users.getUserName();
			String password = users.getPassword();
			System.out.println("get username: "+username+"get password: "+password);
		} else {
			throw new RuntimeException(" User not found for id " + id);
		}
		return null;
	}

	
	@Override
	public Users findUserUsingEnhancedForLoop(String names,String password) {
	
	
		
	    for (Users user : getallUsers()) {
	    	System.out.println("User Name: "+user.getUserName()+" ,User Password: "+user.getPassword()+" ,User Role: "+user.getRoles());
	    
	        if (user.getUserName().equals(names) && user.getPassword().equals(password)) {
	        	System.out.println(user.getUserName()+" loged in");
	        	
	            return user;
	        }
	      
	    }
	    return null;
	}

	
	
	
	
	

}
