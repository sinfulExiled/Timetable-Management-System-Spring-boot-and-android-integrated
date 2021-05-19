package com.cb007727.EEATimeTableManager.services;

import java.util.List;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Holidays;
import com.cb007727.EEATimeTableManager.models.Rooms;

public interface HolidaysService {
	void saveHolidays(Holidays holidays);
	List<Holidays> getAllHolidays();
	void deleteHolidaysById(long id);
}
