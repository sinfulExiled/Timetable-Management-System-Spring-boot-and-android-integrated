package com.cb007727.EEATimeTableManager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Rooms;
import com.cb007727.EEATimeTableManager.repository.RoomsRepository;

@Service
public class RoomsServiceIMPL implements RoomsService {

	@Autowired
	private RoomsRepository roomsRepository;
	
	@Override
	public List<Rooms> getAllRooms() {
		return roomsRepository.findAll();
	}

	@Override
	public void saveRooms(Rooms rooms) {
		this.roomsRepository.save(rooms);
		
	}

	@Override
	public Rooms getRoomsById(long id) {
		Optional<Rooms> optional = roomsRepository.findById(id);
		Rooms rooms = null;
		if (optional.isPresent()) {
			rooms = optional.get();
		} else {
			throw new RuntimeException(" Room not found for id :: " + id);
		}
		return rooms;
	}

	@Override
	public void deleteRoomsById(long id) {
		this.roomsRepository.deleteById(id);
		
	}

	@Override
	public Page<Rooms> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.roomsRepository.findAll(pageable);
	}

	@Override
	public List<Rooms> getRoomsforBatch(String available) {
		
		List<Rooms> getRooms = new ArrayList<>();
		
		for (Rooms rooms : getAllRooms()) {
			
			System.out.println(" classes date: "+rooms.getNumber()+" classes batch: "+rooms.getFloor());
			if(rooms.getAvailability().equals(available)) {
				getRooms.add(rooms);
			}
			
		}
		return getRooms;
	}
	

}
