package com.ashokit.service;

import java.util.List;

import com.ashokit.bindings.SearchCriteria;
import com.ashokit.entity.StudentEnq;

public interface EnquiryService {
	
	public boolean addEnq(StudentEnq se);

	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);

}
