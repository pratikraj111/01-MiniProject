package com.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.SearchCriteria;
import com.ashokit.entity.StudentEnq;
import com.ashokit.repo.StudentEnqRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private StudentEnqRepo srepo;

	@Override
	public boolean addEnq(StudentEnq se) {
		
		StudentEnq s1=srepo.save(se);
		
		if( s1.getEnqId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s) {
		// TODO Auto-generated method stub
		return null;
	}

}
