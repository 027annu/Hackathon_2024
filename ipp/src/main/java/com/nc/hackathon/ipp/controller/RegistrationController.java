package com.nc.hackathon.ipp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nc.hackathon.ipp.model.UserLoginPageMapper;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.model.constant.ConstantData;
import com.nc.hackathon.ipp.service.RegistrationService;

/**
 * This controller is to handle all requests related to registration
 * @author sugh1218
 *
 */
@Controller
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	
	@RequestMapping(path = "/ncjobsearch/registration", method=RequestMethod.POST)
	public ModelAndView showRegistrationPage(ModelAndView register) {
		
		register.addObject("userRegistration", new UserRegistration());
		
		register.addObject("allRoles", ConstantData.ROLES.getAllRoles());
		register.addObject("allQualifications", ConstantData.EDUCATION.getAllEducations());
		register.addObject("allProjects", ConstantData.PROJECTS.getAllProjects());
		register.addObject("allSkills", ConstantData.SKILLS.getAllSkills());
		
		register.setViewName("registrationPage");
		return register;
	}
	
	
	@RequestMapping(path = "/ncjobsearch/registernow", method=RequestMethod.POST)
	public String needToRegister(@Valid @ModelAttribute UserRegistration userRegistration, 								
										BindingResult result,  
										Model register, 
										@RequestParam("uploadedResume") MultipartFile resume, 
										HttpSession session) throws IOException {
				
		register.addAttribute("allRoles", ConstantData.ROLES.getAllRoles());
		register.addAttribute("allQualifications", ConstantData.EDUCATION.getAllEducations());
		register.addAttribute("allProjects", ConstantData.PROJECTS.getAllProjects());
		register.addAttribute("allSkills", ConstantData.SKILLS.getAllSkills());
		
		
		register.addAttribute("TestMessage", "This Is Test Message");
		if (result.hasErrors() || !userRegistration.getPassword().equals(userRegistration.getConfirmPassword())) {
				
			System.out.println("==========================================result.hasErrors()=======================================" + result.getAllErrors());
			if (!userRegistration.getPassword().equals(userRegistration.getConfirmPassword())) {
				register.addAttribute("passwordMismatch", "Password and Confirm Password did not match, please try again");
			}
			return "registrationPage";
		}

		if (resume != null && !(resume.getContentType().contains("pdf") || resume.getContentType().contains("doc"))) {		
			register.addAttribute("incorrectResumeFormat", "Resume must be of format .doc, .docx or .pdf.");
			return "registrationPage";
		}
				
		boolean userExists = registrationService.checkAndThenCreateUser(userRegistration, resume);
		
		if (userExists == true) {
			register.addAttribute("userAlreadyExistsError", "User Id already regestered, please go to login, If issue still exists please contact admin.");
			return "registrationPage";
		}
			
		register.addAttribute("userCanLoginNowMsg", "You are successfully registered, Please try to login now");
		register.addAttribute("userlogin", new UserLoginPageMapper());
		return "loginPage";
	}
	

	
}
