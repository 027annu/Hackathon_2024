package com.nc.hackathon.ipp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.model.constant.ConstantData;
import com.nc.hackathon.ipp.service.LoginService;
import com.nc.hackathon.ipp.service.RegistrationService;
import com.nc.hackathon.ipp.service.dao.RegistrationDaoService;

@Controller
public class ProfileController {

	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private RegistrationDaoService userRegistrationService;
	
	@Autowired
	private RegistrationService registartionService;
	
	
	@RequestMapping(path = "/ncjobsearch/myProfile")
	public String getMyProfile(Model m) {
		
		String userId = loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(userId);
	
		if (userId != null) {
			m.addAttribute("userName", user.getName());
			m.addAttribute("userId", user.getNcid());
			m.addAttribute("userProfile", userRegistrationService.findById(userId).get());
			m.addAttribute("allRoles", ConstantData.ROLES.getAllRoles());
			m.addAttribute("allQualifications", ConstantData.EDUCATION.getAllEducations());
			m.addAttribute("allProjects", ConstantData.PROJECTS.getAllProjects());
			m.addAttribute("allSkills", ConstantData.SKILLS.getAllSkills());
			System.out.println("Reloading Demand Page");

	  			  String userAccess =
	  					  loginService.getLoggedInUser(userId).getUserAccessLevel();
	  			  System.out.println("userAccess ==>" + userAccess);
	  			m.addAttribute("userAccess", userAccess); 
	  		  
			return "MyProfile";
		}
		return "errorPage";
		
		
	}
	
	@RequestMapping(path = "/ncjobsearch/updateProfile", method = RequestMethod.POST)
	public String updateProfile(UserRegistration userRegistration, 								
			BindingResult result,  
			Model register, 
			@RequestParam("uploadedResume") MultipartFile resume) throws IOException {
		System.out.println("Updating Profile");
		
		
		//Save Old Resume Path 
		if (resume != null && resume.getSize() == 0) {
			UserRegistration oldData = userRegistrationService.findById(userRegistration.getNcId()).get();
			String resumepath = oldData.getResumePath();
			userRegistration.setResumePath(resumepath);
		}
		
		registartionService.checkAndUpdateUser(userRegistration, resume);
		return "redirect:myProfile";
	}
 
}
