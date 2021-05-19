package com.cb007727.EEATimeTableManager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Students;
import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.StudentService;
import com.cb007727.EEATimeTableManager.services.UsersService;

@Controller
public class StudentsController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ClassesService classesService;
	
	
	@GetMapping("/signUpView")
	public String ViewSignUpPage(@ModelAttribute Students students, BindingResult bindingResult, Model model) {
		model.addAttribute("studentsObj", students);
		return "signUp";
	}
	
	
	
	@PostMapping("/addStudent")
	public String saveStudent(@ModelAttribute("students") Students student) {
		
		
		String email = student.getEmailAddress();
		String password = student.getPassword();
		System.out.println("print ");
		Users user = new Users(email,password,"student");
		saveUsers(user);
		// save students to database
		studentService.saveStudents(student);
		
		return "redirect:/logInView";
	}
	
	public void saveUsers(@ModelAttribute("users") Users user) {
		// save users to database
		
		usersService.saveUsers(user);
		
	}
	
	@GetMapping("/batchtimetableList/{batchName}")
	public String findclassesforBatch(@PathVariable (value = "batchName") String bactchName,@ModelAttribute("classes") Classes cla,
			Model model) {
		
		List<Classes> batchClassList = classesService.getClassesforBatch(bactchName);
		for (Classes classes : batchClassList) {
			System.out.println(" classes date: "+classes.getDate()+" classes batch: "+classes.getBatch());
		}
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		model.addAttribute("batchClassList", batchClassList);
		return "studentHome";
	}
}
