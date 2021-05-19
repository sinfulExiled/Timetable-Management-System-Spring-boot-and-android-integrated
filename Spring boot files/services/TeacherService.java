package com.cb007727.EEATimeTableManager.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cb007727.EEATimeTableManager.models.Teachers;

public interface TeacherService {

	List<Teachers> getAllTeachers();
	List<Teachers> getTeachersforBatch(String batchName);
	void saveTeachers(Teachers teachers);
	Teachers getTeachersById(long id);
	void deleteTeachersById(long id);
	Page<Teachers> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
