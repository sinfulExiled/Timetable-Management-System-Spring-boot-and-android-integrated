package com.cb007727.EEATimeTableManager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Holidays;
import com.cb007727.EEATimeTableManager.models.Rooms;
import com.cb007727.EEATimeTableManager.services.ClassesService;
import com.cb007727.EEATimeTableManager.services.HolidaysService;
import com.cb007727.EEATimeTableManager.services.RoomsService;


@Component
public class AutomaticDataBaseChecker {

	@Autowired
	private RoomsService roomsService;
	
	@Autowired
	private ClassesService classesService;
	
	@Autowired
	private HolidaysService holidaysService;
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void run() {
		try{
		List<Rooms> rooms = new ArrayList<>();
		rooms =	roomsService.getAllRooms();
		
		List<Classes> classes= new ArrayList<>();
		classes =	classesService.getAllClasses();
		
		List<Holidays> holidays= new ArrayList<>();
		holidays =	holidaysService.getAllHolidays();
		
		String roomReserveDate = "";
		String roomEndTime = "";
		
		String classDate = "";
		String classEndTime = "";

		String holidaysDate = "";
		
		String todaysDate = "";
		
		for(Rooms r : rooms) {
			roomReserveDate = r.getReservedDate();
			roomEndTime = r.getEndTime();
			

			String conactinateDate = roomReserveDate+" "+roomEndTime;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	        Date date1 = sdf.parse(conactinateDate);
	        Date date2 = sdf.parse("2020/10/10 14:20");
	        Date date = new Date();  
	        todaysDate = sdf.format(date);  
	        date = sdf.parse(todaysDate);
	        if(date1.before(date)) {
		        roomsService.deleteRoomsById(r.getId());
	        }
			
		}
		
		
		for(Classes r : classes) {
			classDate = r.getDate();
			classEndTime = r.getEndTime();
			

			String conactinateDate = classDate+" "+classEndTime;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	        Date date1 = sdf.parse(conactinateDate);
	        Date date = new Date();  
	        todaysDate = sdf.format(date);  
	        date = sdf.parse(todaysDate);
	        if(date1.before(date)) {
		        classesService.deleteClassesById(r.getId());
	        }
			
		}
		
		for(Holidays r : holidays) {
			classDate = r.getHolidayDate();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	        Date date1 = sdf.parse(classDate);
	        Date date = new Date();  
	        todaysDate = sdf.format(date);  
	        date = sdf.parse(todaysDate);
	        if(date1.before(date)) {
		        holidaysService.deleteHolidaysById(r.getId());
	        }
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
