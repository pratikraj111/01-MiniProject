package com.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashokit.bindings.SearchCriteria;
import com.ashokit.entity.StudentEnq;

@Service
public class EnquirySerImpl implements EnquiryService {

	@Override
	public boolean addEnq(StudentEnq se) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s) {
		// TODO Auto-generated method stub
		return null;
	}

}
