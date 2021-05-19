package com.cb007727.EEATimeTableManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cb007727.EEATimeTableManager.models.Holidays;
import com.cb007727.EEATimeTableManager.services.HolidaysService;
@Controller
public class HolidaysController {

	@Autowired
	HolidaysService holidaysService;
	
	
	@GetMapping("/viewStuHolidays")
	public String viewStuHolidays(@ModelAttribute Holidays holidays, BindingResult bindingResult, Model model) {
		List<Holidays> holi = holidaysService.getAllHolidays();
		for(Holidays holiday : holi) {
			System.out.println(holiday.getHolidayName());
		}
		model.addAttribute("HolidaysList",holi);
		return "holidaysView";
	}
}
