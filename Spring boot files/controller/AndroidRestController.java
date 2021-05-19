package com.cb007727.EEATimeTableManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Holidays;
import com.cb007727.EEATimeTableManager.models.Rooms;
import com.cb007727.EEATimeTableManager.models.Students;
import com.cb007727.EEATimeTableManager.models.Teachers;
import com.cb007727.EEATimeTableManager.models.Users;
import com.cb007727.EEATimeTableManager.repository.ClassesRepository;
import com.cb007727.EEATimeTableManager.repository.HolidaysRepository;
import com.cb007727.EEATimeTableManager.repository.RoomsRepository;
import com.cb007727.EEATimeTableManager.repository.StudentsRepository;
import com.cb007727.EEATimeTableManager.repository.TeachersRepository;
import com.cb007727.EEATimeTableManager.repository.UsersRepository;
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.HolidaysService;
import com.cb007727.EEATimeTableManager.services.RoomsService;
import com.cb007727.EEATimeTableManager.services.StudentService;
import com.cb007727.EEATimeTableManager.services.TeacherService;
import com.cb007727.EEATimeTableManager.services.UsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AndroidRestController {


	
	@Autowired
	private ClassesService classesService;


	@Autowired
	private HolidaysService holidaysService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private RoomsService roomsService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RoomsRepository roomsRepository;
	
	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private HolidaysRepository holidaysRepository;
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Autowired
	private StudentsRepository studentsRepository;

	
	
	@GetMapping("/classes")
	public List<Classes> getAllClasses(){
		return classesRepository.findAll();
		
	}
	
	@PostMapping("/classes")
	public Classes createClasses(@RequestBody Classes classes) {
		return classesRepository.save(classes);
	}
	
	@GetMapping("/holidays")
	public List<Holidays> getAllHolidays() {
		return holidaysRepository.findAll();
	}
	
	@GetMapping("/deleteHolidays/{id}")
	public void deleteHolidays(@PathVariable (value = "id") long id) {
		// call delete classes method 
		this.holidaysService.deleteHolidaysById(id);
	
	}
	
	@GetMapping("/teachers")
	public List<Teachers> getAllTeachers() {
		return teachersRepository.findAll();
	}
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@PostMapping("/holidaysSave")
	public void saveHolidays(@RequestBody Holidays holidays) {
		// save classes to database
		holidaysService.saveHolidays(holidays);
		
	}
	
	@PostMapping("/classesSave")
	public void saveClasses(@RequestBody Classes classes) {
		// save classes to database
		classesService.saveClasses(classes);
		
	}
	
	@GetMapping("/deleteClasses/{id}")
	public void deleteClasses(@PathVariable (value = "id") long id) {
		// call delete classes method 
		this.classesService.deleteClassesById(id);
	
	}

	@PostMapping("/teachersSave")
	public void saveTeachers(@RequestBody Teachers teacher) {
		// save Teachersteachers to database
		teacherService.saveTeachers(teacher);
		
	}
	
	@GetMapping("/deleteTeachers/{id}")
	public void deleteTeachers(@PathVariable (value = "id") long id) {
		// call delete Teachers method 
		this.teacherService.deleteTeachersById(id);
	
	}
	
	@GetMapping("/rooms")
	public List<Rooms> getAllRooms() {
		return roomsRepository.findAll();
	}
	
	
	@PostMapping("/roomsSave")
	public void saveRooms(@RequestBody Rooms rooms) {
		roomsService.saveRooms(rooms);
		
	}
	
	@GetMapping("/deleteRooms/{id}")
	public void deleteRooms(@PathVariable (value = "id") long id) {
		this.roomsService.deleteRoomsById(id);
	
	}
	
	@PostMapping("/registerStudent")
	public void registerStudent(@RequestBody Students student) {
		
		String email = student.getEmailAddress();
		String password = student.getPassword();
		System.out.println("print "+email);
		System.out.println("print "+password);
		Users user = new Users(email,password,"student");
		userService.saveUsers(user);
		// save students to database
		studentService.saveStudents(student);
		
	}
	
	@GetMapping("/students")
	public List<Students> getAllStudents() {
		return studentsRepository.findAll();
	}
	
	
	
	
}