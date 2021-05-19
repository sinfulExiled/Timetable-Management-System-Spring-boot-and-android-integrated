package com.cb007727.EEATimeTableManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb007727.EEATimeTableManager.models.Enquiry;
import com.cb007727.EEATimeTableManager.repository.EnquiryRepository;

@Service
public class MainServicesIMPL implements MainService{
	
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Override
	public void saveEnquiry(Enquiry enquiry) {
		this.enquiryRepository.save(enquiry);
		
	}	

}