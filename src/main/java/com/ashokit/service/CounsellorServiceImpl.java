package com.ashokit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.entity.StudentEnq;
import com.ashokit.repo.CounsellorRepo;
import com.ashokit.repo.StudentEnqRepo;
import com.ashokit.util.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired 
	private EmailUtils emailUtils;
	
	@Autowired
	private StudentEnqRepo sRepo;
	

	@Override
	public String saveCounsellor(Counsellor c) {
		
		Counsellor obj=counsellorRepo.findByEmail(c.getEmail());
		
		if(obj!=null)
		{
			return "Email is duplicated..";
		}
		
		Counsellor c1=counsellorRepo.save(c);
		if(c1.getCid()!=null)
		{
			return "Registration successfull..!";
		}
		return "Registration failed..!";
		
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		
		return counsellorRepo.findByEmailAndPwd(email, pwd);
		
		 
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor obj=counsellorRepo.findByEmail(email);
		if(obj==null)
		{
			return false;
		}
		String subject="Recover password- Pratik";
		String body="<h1> Your Password: "+obj.getPwd()+"</h1>";
		
		return emailUtils.sendMail(subject, body, email);
		
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		
		 List<StudentEnq> students=sRepo.findByCid(cid);
		 
		 int enrolledEnq=students.stream()
				 				.filter(e->e.getStatus().equals("Enrolled"))
				 				.collect(Collectors.toList())
				 				.size();
		 
		 DashboardResponse resp=new DashboardResponse();
		 resp.setEnrollerEnq(enrolledEnq);
		 resp.setTotalEnq(students.size());
		 resp.setLostEnq(students.size()-enrolledEnq);
		 
		 return resp;
				 
				 
	}
	
}
