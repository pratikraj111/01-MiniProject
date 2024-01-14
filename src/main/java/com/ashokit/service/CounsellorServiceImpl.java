package com.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	

	@Override
	public String saveCounsellor(Counsellor c) {
		
		Counsellor c1=counsellorRepo.save(c);
		if(c1.getCid()!=null)
		{
			return "Registration successfull..!";
		}
		return "Registration failed..!";
		
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		
		Counsellor c= counsellorRepo.findByEmailAndPwd(email, pwd);
		
		return c;
	}

	@Override
	public boolean recoverPwd(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

}
