package com.cb007727.EEATimeTableManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Holidays;
import com.cb007727.EEATimeTableManager.repository.HolidaysRepository;


@Service
public class HolidaysServiceIMPL implements HolidaysService{

	@Autowired
	private HolidaysRepository holidaysRepository;

	@Override
	public void saveHolidays(Holidays holidays) {
		this.holidaysRepository.save(holidays);
		
	}

	@Override
	public List<Holidays> getAllHolidays() {
		return holidaysRepository.findAll();

	}

	@Override
	public void deleteHolidaysById(long id) {
		this.holidaysRepository.deleteById(id);
	}
}
