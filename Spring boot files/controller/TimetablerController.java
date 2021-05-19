package com.cb007727.EEATimeTableManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Rooms;
import com.cb007727.EEATimeTableManager.models.Students;
import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.services.ClassesService;

@Controller
public class TimetablerController {
	
	@Autowired
	private ClassesService classesService;
	
	@PostMapping("/makeTimetableRow")
	public String makeTimetableRow(@ModelAttribute("classes") Classes classes, BindingResult bindingResult, Model model) {
		model.addAttribute("classes",classes);
		classesService.saveClasses(classes);
		return "timetablerHome";
	}
	
	
	@RequestMapping(value = "/gotomakeTimetableRow", method = RequestMethod.GET)
	public String gotomakeTimetableRow(@ModelAttribute("classes") Classes classes, BindingResult bindingResult, Model model) {
		model.addAttribute("classes",classes);
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		return "timetablerHome";
	}
	
	@GetMapping("/timetableEdit")
	public String timeTableEdit(Model model) {
		
		return findPaginated(1, "date", "asc", model);	
	}
	

	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get classes from the service
		Classes classes = classesService.getClassesById(id);
		
		// set classes as a model attribute to pre-populate the form
		model.addAttribute("Classes", classes);
		return "editTimetableForm";
	}
	
	@PostMapping("/saveClasses")
	public String saveClasses(@ModelAttribute("classes") Classes classes) {
		// save classes to database
		classesService.saveClasses(classes);
		return "redirect:/timetableEdit";
	}
	
	
	@GetMapping("/deleteClasses/{id}")
	public String deleteClasses(@PathVariable (value = "id") long id) {
		
		// call delete classes method 
		this.classesService.deleteClassesById(id);
		return "redirect:/timetableEdit";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Classes> page = classesService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Classes> listClasses = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClasses", listClasses);
		return "editTimetable";
	}
	
	
}
