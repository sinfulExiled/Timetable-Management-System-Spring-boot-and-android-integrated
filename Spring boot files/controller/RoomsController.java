package com.cb007727.EEATimeTableManager.controller;

import java.util.ArrayList;
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
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.RoomsService;

@Controller
public class RoomsController {
	
	@Autowired
	private RoomsService roomsService;
	@Autowired
	private ClassesService classesService;
	
	@PostMapping("/makeRoomsRow")
	public String makeRoomsRow(@ModelAttribute("rooms") Rooms rooms, BindingResult bindingResult, Model model) {
		model.addAttribute("Rooms",rooms);
		roomsService.saveRooms(rooms);
		return "/";
	}

	@RequestMapping(value = "/gotomakeRoomsRow", method = RequestMethod.GET)
	public String gotomakeRoomsRow(@ModelAttribute("rooms") Rooms rooms, BindingResult bindingResult, Model model) {
		model.addAttribute("Rooms",rooms);
		return "/";
	}
	
	@GetMapping("/editRooms")
	public String editRooms(Model model) {
		
		return findPaginatedRooms(1, "number", "asc", model);	
	}
	

	@PostMapping("/saveRooms")
	public String saveRooms(@ModelAttribute("rooms") Rooms Rooms) {
		// save Rooms to database
		roomsService.saveRooms(Rooms);
		return "editRooms";
	}
	
	@GetMapping("/showRoomsFormForUpdate/{id}")
	public String showRoomsFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Rooms from the service
		Rooms Rooms = roomsService.getRoomsById(id);
		
		// set Rooms as a model attribute to pre-populate the form
		model.addAttribute("Rooms", Rooms);
		return "editTimetableForm";
	}
	
	
	@GetMapping("/deleteRooms/{id}")
	public String deleteRooms(@PathVariable (value = "id") long id) {
		
		// call delete Rooms method 
		this.roomsService.deleteRoomsById(id);
		return "redirect:/editRooms";
	}
	
	
	
	@GetMapping("/pagerooms/{pageNo}")
	public String findPaginatedRooms(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Rooms> page = roomsService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Rooms> listRooms = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listRooms", listRooms);
		return "editRooms";
	}
	
	@GetMapping("/pageroomsstu/{pageNo}")
	public String findPaginatedRoomsStu(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Rooms> page = roomsService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Rooms> listRooms = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listRooms", listRooms);
		return "rooms";
	}
	
	@GetMapping("/teacherRoomspage")
	public String teachereditRooms(Model model) {
		
		return findPaginatedRoomsteacher(1, "number", "asc", model);	
	}
	
	@GetMapping("/teacherroomrespage/{pageNo}")
	public String findPaginatedRoomsteacher(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Rooms> page = roomsService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Rooms> listRooms = page.getContent();
		
	
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("RoomsLists", listRooms);
		return "teacherRoomReservation";
	} 
	
	@GetMapping("/timetablerRoomspage")
	public String timetablerRoomspage(Model model) {
		
		return findPaginatedRoomsTimetabler(1, "number", "asc", model);	
	}
	
	@GetMapping("/pagetimetablerroom/{pageNo}")
	public String findPaginatedRoomsTimetabler(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Rooms> page = roomsService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Rooms> listRooms = page.getContent();
		List<Rooms> pendingList = new ArrayList<>();
		
		for(Rooms rl : listRooms) {
			System.out.println(rl.getStatus());
		
			if(rl.getStatus().equals("pending")) {
				pendingList.add(rl);
			}
		}
	
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("RoomsLists", pendingList);
		return "timeTablerRoomReservation";
	} 
	@GetMapping("/RoomReservationConfirm/{id}")
	public String RoomReservationConfirm(@PathVariable ( value = "id") long id, Model model) {
		
		// get Rooms from the service
		Rooms rooms = roomsService.getRoomsById(id);
		rooms.setStatus("booked");
		System.out.println(rooms.getId()+" get " + id);
		 roomsService.saveRooms(rooms);
		// set Rooms as a model attribute to pre-populate the form
		model.addAttribute("Rooms", rooms);
		return "redirect:/timetablerRoomspage";
	}
	
	
	
	@RequestMapping(value = "/gotoTeachRooms", method = RequestMethod.GET)
	public String toStuRooms(@ModelAttribute("rooms") Rooms rooms, BindingResult bindingResult, Model model) {
		model.addAttribute("RoomsList",roomsService.getAllRooms());
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		return "roomsTeacher";
	}
	@GetMapping("/TeaRooms/{availability}")
	public String StuRoomsPagination(@PathVariable (value = "availability") String availability,@ModelAttribute("rooms") Rooms cla,
			Model model) {
		
		List<Rooms> roomsList = roomsService.getRoomsforBatch(availability);
		for (Rooms rooms : roomsList) {
			System.out.println(" classes date: "+rooms.getAvailability()+" classes batch: "+rooms.getNumber());
		}
		String email = LogInController.email;
		model.addAttribute("mykey", email);
		model.addAttribute("RoomsList", roomsList);
		return "roomsTeacher";
	}
	
	@GetMapping("/showFormForRoomReservation/{id}")
	public String showFormForRoomsReservat(@PathVariable ( value = "id") long id, Model model) {
		
		// get rooms from the service
		Rooms rooms = roomsService.getRoomsById(id);
		List<Classes> classes = classesService.getAllClasses();
		List<Classes> bookedClasses = new ArrayList<>();
		for(Classes cl : classes) {
			
			String cla = cl.getVenue();
			String roonNumber = rooms.getNumber();
			System.out.println(cla+"|||||||"+roonNumber);
			if(cla.equals(rooms.getNumber())) {
				
				bookedClasses.add(cl);
			}
			
		}
		String email = LogInController.email;
		rooms.setBookedTeacher(email);
		
		// set rooms as a model attribute to pre-populate the form
		model.addAttribute("Room", rooms);
		model.addAttribute("bookedClasses", bookedClasses);
		return "teacherRoomReservationSelect";
	}
	
	@PostMapping("/makeRoomReservation")
	public String saveClasses(@ModelAttribute("rooms") Rooms rooms) {
		// save classes to database
		String starttime = rooms.getStartTime();
		rooms.setStatus("pending");
		rooms.setAvailability("unavailable");
		System.out.println(starttime);
		 roomsService.saveRooms(rooms);
		return "redirect:/teacherRoomspage";
	}
	
	
}
