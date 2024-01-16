package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.bindings.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.service.CounsellorService;
import com.ashokit.util.EmailUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorsController {
	
	@Autowired
	private CounsellorService counsellorSer;

	
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("Counsellor", new Counsellor());
		return "loginView";
	}
	
	@PostMapping("/login")
	public String handleLogin(Counsellor c,HttpServletRequest req, Model model)
	{
		Counsellor c1=counsellorSer.loginCheck(c.getEmail(), c.getPwd());
		
		if(c1== null)
		{
			model.addAttribute("ErrMsg", "Invalid Credentials");
			return "loginView";
		}
		
		HttpSession session=req.getSession(true);
		session.setAttribute("CID", c1.getCid());
		
		return "redirect:dashboard";
	}

	@GetMapping("/register")
	public String regPage(Model model)
	{
		model.addAttribute("Counsellor", new Counsellor());
		return "registerView";
	}

	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model)
	{
		String msg=counsellorSer.saveCounsellor(c);
		model.addAttribute("msg", msg);
		model.addAttribute("Counsellor", new Counsellor());
		return "registerView";
	}
	
	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model)
	{
		return "forgotPwdView";
	}

	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("CID");
		
		DashboardResponse dashboardInfo=counsellorSer.getDashboardInfo(cid);
		model.addAttribute("dboard", dashboardInfo);
		
		return "dashboard";
	}
	
	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email, Model model)
	{
		Boolean stts=counsellorSer.recoverPwd(email);
		if(stts)
		{
			model.addAttribute("msg", "Password sent on mail successfully..!");
		}
		else
		{
			model.addAttribute("errmsg", "Invalid email Id");
		}
		return "forgotPwdView";
	}
}
