package com.cb007727.EEATimeTableManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb007727.EEATimeTableManager.models.Students;
import com.cb007727.EEATimeTableManager.repository.StudentsRepository;

@Service
public class StudentServiceIMPL implements StudentService {

	@Autowired
	private StudentsRepository studentsRepository;
	
	@Override
	public void saveStudents(Students student) {
		this.studentsRepository.save(student);
		
	}

}
