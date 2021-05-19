package com.cb007727.EEATimeTableManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Teachers;
import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.TeacherService;
import com.cb007727.EEATimeTableManager.services.UsersService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private ClassesService classesService;
	
	@GetMapping("/goaddTeacher")
	public String ViewSignUpPage(@ModelAttribute Teachers teachers, Model model) {
		model.addAttribute("teachersObj", teachers);
		return "addTeacherForm";
	}
	
	@GetMapping("/batchtimetableListTeach/{batchName}")
	public String findclassesforBatchteach(@PathVariable (value = "batchName") String bactchName,@ModelAttribute("classes") Classes cla,
			Model model) {
		
		List<Classes> batchClassList = classesService.getClassesforBatch(bactchName);
		for (Classes classes : batchClassList) {
			System.out.println(" classes date: "+classes.getDate()+" classes batch: "+classes.getBatch());
		}
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		model.addAttribute("batchClassList", batchClassList);
		return "teacherHome";
	}
	
	@GetMapping("/viewTeacherHolidays")
	public String viewStuHolidays(@ModelAttribute Teachers teacher, BindingResult bindingResult, Model model) {
		model.addAttribute("teacherObj", teacher);
		return "teacherHolidays";
	}
	
	@RequestMapping(value = "/TeacherHome", method = RequestMethod.GET)
	public String gotomakeTimetableRow(@ModelAttribute("classes") Classes classes, BindingResult bindingResult, Model model) {
		model.addAttribute("classes",classes);
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		return "teacherHome";
	}

	
	@PostMapping("/addTeacher")
	public String saveStudent(@ModelAttribute("teachers") Teachers teachers) {
		
		
		String email = teachers.getEmailAddress();
		String password = teachers.getPassword();
		
		Users user = new Users(email,password,"teacher");
		
		// save teachers to database

		saveUsers(user);
		teacherService.saveTeachers(teachers);
		
		return "redirect:/goaddTeacher";
	}
	
	public void saveUsers(@ModelAttribute("users") Users user) {
		// save users to database
		
		usersService.saveUsers(user);
		
	}
}
