package com.ashokit.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.bindings.SearchCriteria;
import com.ashokit.entity.StudentEnq;
import com.ashokit.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	

	
	@GetMapping("/enquiry")
	public String enqPage(Model model)
	{
		model.addAttribute("enq", new StudentEnq());
		return "addEnqView";
	}
	
	@PostMapping("/enquiry")
	public String addEnquiry(StudentEnq enq,HttpServletRequest req, Model model)
	{
		HttpSession session= req.getSession(false);
		Object obj=session.getAttribute("CID");
		Integer cid=(Integer)obj;
		enq.setCid(cid);
		Boolean stts=enqService.addEnq(enq);
		model.addAttribute("enq", new StudentEnq());
	
		enq.setCreatedDate(java.time.LocalDate.now());
		
		if(stts)
		{
			model.addAttribute("msg", "Enquiry added successfully..!");
		}
		else
		{
			model.addAttribute("errMsg", "Invalid input..!");
		}
		return "addEnqView";
	}

	

	@GetMapping("/Enquiries")
	public String viewEnquiries(Model model)
	{
		List<StudentEnq> enquiriesList=enqService.getEnquiries(null, null);
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
	}

	@GetMapping("/Filter-Enquiries")
	public String filterEnquiries(SearchCriteria sc, Model model)
	{
		List<StudentEnq> enquiriesList=enqService.getEnquiries(null, null);
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
	}
}
