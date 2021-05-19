package com.cb007727.EEATimeTableManager.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Rooms;

public interface RoomsService {
	List<Rooms> getAllRooms();
	void saveRooms(Rooms rooms);
	Rooms getRoomsById(long id);
	void deleteRoomsById(long id);
	Page<Rooms> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	public List<Rooms> getRoomsforBatch(String batchName);
}
