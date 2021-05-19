package com.cb007727.EEATimeTableManager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.services.UsersService;

@Controller
public class LogInController {
	@Autowired
	UsersService usersService;
	
	@Value("${useremail}")
	public static String email;

	@GetMapping("/logInView")
	public String ViewlogInPage(@ModelAttribute Users users, BindingResult bindingResult, Model model) {
		model.addAttribute("usersObj", users);
		return "logIn";
	}
	
	@PostMapping("/loginUser")
	public String logInUser(@ModelAttribute("users") Users usersobj, BindingResult bindingResult, Model model) {
	
		email = usersobj.getUserName();
		String password = usersobj.getPassword();
	
		Users verifyuser = new Users();
	
		Classes classes = new Classes();
		verifyuser = usersService.findUserUsingEnhancedForLoop(email,password);
		String returnUserType = verifyuser.getRoles();
		model.addAttribute("mykey", email);
		model.addAttribute("classes", classes);
		switch(returnUserType) {
		case "student":
			
			
			return "studentHome";
			
		case "teacher":
			
			return "teacherHome";
		
		case "admin":
			model.addAttribute("classes", classes);
			
			return "timetablerHome";
			
		default:
			return "redirect:/logInView";
	
		}
		
	
	}
	
	
	
}
