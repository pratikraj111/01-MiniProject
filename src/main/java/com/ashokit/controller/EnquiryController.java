package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.bindings.SearchCriteria;
import com.ashokit.entity.StudentEnq;
import com.ashokit.service.EnquiryService;

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
	public String addEnquiry(StudentEnq se, Model model)
	{
		Boolean stts=enqService.addEnq(se);
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
