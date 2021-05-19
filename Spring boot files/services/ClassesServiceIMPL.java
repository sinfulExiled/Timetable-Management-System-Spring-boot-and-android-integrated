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
import com.cb007727.EEATimeTableManager.repository.ClassesRepository;

@Service
public class ClassesServiceIMPL implements ClassesService {

	@Autowired
	private ClassesRepository classesRepository;
	
	@Override
	public List<Classes> getAllClasses() {
		return classesRepository.findAll();
	}

	@Override
	public void saveClasses(Classes classes) {
		this.classesRepository.save(classes);
		
	}

	@Override
	public Classes getClassesById(long id) {
		Optional<Classes> optional = classesRepository.findById(id);
		Classes classes = null;
		if (optional.isPresent()) {
			classes = optional.get();
		} else {
			throw new RuntimeException(" Classes not found for id :: " + id);
		}
		return classes;
	}

	@Override
	public void deleteClassesById(long id) {
		this.classesRepository.deleteById(id);
		
	}

	@Override
	public Page<Classes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.classesRepository.findAll(pageable);
	}

	@Override
	public List<Classes> getClassesforBatch(String batchName) {
		
		List<Classes> getClasses = new ArrayList<>();
		
		for (Classes classes : getAllClasses()) {
			
			System.out.println(" classes date: "+classes.getDate()+" classes batch: "+classes.getBatch());
			if(classes.getBatch().equals(batchName)) {
				getClasses.add(classes);
			}
			
		}
		return getClasses;
	}

	

}
