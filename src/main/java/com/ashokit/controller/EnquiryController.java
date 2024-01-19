package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String addEnquiry(@ModelAttribute("enq") StudentEnq enq,HttpServletRequest req, Model model)
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
	public String viewEnquiries(HttpServletRequest req,Model model)
	{
		HttpSession session= req.getSession(false);
		Object obj=session.getAttribute("CID");
		Integer cid=(Integer)obj;
		
		if(cid==null)
		{
			return "redirect:/logout";
		}
		
		model.addAttribute("sc", new SearchCriteria());
		
		List<StudentEnq> enquiriesList=enqService.getEnquiries(cid, new SearchCriteria());
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
	}

	@PostMapping("/Filter-Enquiries")
	public String filterEnquiries(@ModelAttribute("sc") SearchCriteria sc,HttpServletRequest req, Model model)
	{
		HttpSession session= req.getSession(false);
		Object obj=session.getAttribute("CID");
		Integer cid=(Integer)obj;
		
		if(cid==null)
		{
			return "redirect:/logout";
		}
			
		List<StudentEnq> enquiriesList=enqService.getEnquiries(cid, sc);
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
	}
}
