package com.cb007727.EEATimeTableManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Enquiry;
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.MainService;
import com.cb007727.EEATimeTableManager.services.UsersService;

@Controller
public class MainController {

	//this class handles common objects so 
	
	@Autowired
	private MainService mainService;
	
	

	@Autowired
	private ClassesService classesService;

	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listClasses", classesService.getAllClasses());
		
		
		return "index";
	}
	
	
	@GetMapping("/showAboutPage")
	public String ViewAboutPage() {
		
		return "aboutPage";
	}
	
	
	
	
	
	@GetMapping("/contactUs")
	public String ViewContactPage(@ModelAttribute Enquiry enquiry, BindingResult bindingResult, Model model) {
		model.addAttribute("enquiry", enquiry);
		return "contactUs";
	}
	
	@PostMapping("/makeEnquiry")
	public String saveEnquiry(@ModelAttribute("enquiry") Enquiry enquiry) {
		// save enquiry to database
		mainService.saveEnquiry(enquiry);
		return "redirect:/";
	}

}
