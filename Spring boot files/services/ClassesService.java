package com.cb007727.EEATimeTableManager.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cb007727.EEATimeTableManager.models.Classes;
import com.cb007727.EEATimeTableManager.models.Students;

public interface ClassesService {
	List<Classes> getAllClasses();
	List<Classes> getClassesforBatch(String batchName);
	void saveClasses(Classes classes);
	Classes getClassesById(long id);
	void deleteClassesById(long id);
	Page<Classes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	 
}
