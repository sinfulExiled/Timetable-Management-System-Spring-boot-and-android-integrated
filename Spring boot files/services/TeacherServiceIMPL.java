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
import com.cb007727.EEATimeTableManager.models.Teachers;
import com.cb007727.EEATimeTableManager.repository.TeachersRepository;

@Service
public class TeacherServiceIMPL implements TeacherService {
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Override
	public List<Teachers> getAllTeachers() {
		return teachersRepository.findAll();
	}

	@Override
	public void saveTeachers(Teachers teachers) {
		this.teachersRepository.save(teachers);
		
	}

	@Override
	public Teachers getTeachersById(long id) {
		Optional<Teachers> optional = teachersRepository.findById(id);
		Teachers teachers = null;
		if (optional.isPresent()) {
			teachers = optional.get();
		} else {
			throw new RuntimeException(" Classes not found for id :: " + id);
		}
		return teachers;
	}

	@Override
	public void deleteTeachersById(long id) {
		this.teachersRepository.deleteById(id);
		
	}

	@Override
	public Page<Teachers> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.teachersRepository.findAll(pageable);
	}

	@Override
	public List<Teachers> getTeachersforBatch(String TeacherName) {
		
		List<Teachers> getTeachers = new ArrayList<>();
		
		for (Teachers teachers : getAllTeachers()) {
			
			System.out.println(" Teacher name: "+teachers.getUserName()+" Teachers subject: "+teachers.getTeachingModel());
			if(teachers.getUserName().equals(TeacherName)) {
				getTeachers.add(teachers);
			}
			
		}
		return getTeachers;
	}


}
