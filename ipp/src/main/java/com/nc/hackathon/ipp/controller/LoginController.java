package com.nc.hackathon.ipp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.view.RedirectView;

import com.nc.hackathon.ipp.model.Demand;
import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.model.UserLoginPageMapper;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.model.constant.ConstantData;
import com.nc.hackathon.ipp.repository.DemandRepository;
import com.nc.hackathon.ipp.service.LoginService;
import com.nc.hackathon.ipp.service.RegistrationService;


/**
 * This controller is to handle all request related to login
 * @author sugh1218
 *
 */
@Controller
public class LoginController {

	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private com.nc.hackathon.ipp.service.dao.RegistrationDaoService userRegistrationService;
	
    @Autowired
    private DemandRepository repository;


	@RequestMapping(path = "/ncjobsearch", method = RequestMethod.GET)
	private RedirectView goLoginPage(RedirectView rv) {
		
		rv.setUrl("/ncjobsearch/login");
		System.out.println("ncjobsearch controller is called");
		return rv;
	}
	
	@RequestMapping(path = "/ncjobsearch/login", method = RequestMethod.GET)
	private ModelAndView showLoginPage(ModelAndView loginmv) {
		
		loginmv.addObject("userlogin", new UserLoginPageMapper());
		loginmv.setViewName("loginPage");
		System.out.println("ncjobsearch controller is called");
		return loginmv;
	}
	
	@RequestMapping(path = "/ncjobsearch/internalprojectportal", method=RequestMethod.POST)
	private String validateLogin(@Valid @ModelAttribute("userlogin") UserLoginPageMapper userDetails, BindingResult result, Model m) {
		
		if (result.hasErrors()) {
			System.out.println("result.hasErrors : " + result.getAllErrors());
			return "loginPage";
		}
		
		User user = loginService.validateLogin(userDetails);
		
		//Invalid User Id or Password or both
		if (user == null) {
			m.addAttribute("userlogin", new UserLoginPageMapper());
			m.addAttribute("loginError", "Invalid Username or Password. Please check your credentials and try again.");
			m.addAttribute("loginSuggestion", "New user? Sign up now to get started!");
			return "loginPage";
		}
		
		List<Demand> allDemands = new ArrayList<>();
		for(Demand demand: repository.findAll()){
			System.out.println("Demand Status : "+demand.getStatus());
		   if(demand.getStatus().equals("Open")){
		      allDemands.add(demand);
		   }
		}
	
		
		if (user != null) {
			m.addAttribute("userName", user.getName());
			m.addAttribute("userId", user.getNcid());
			m.addAttribute("userProfile", userRegistrationService.findById(user.getNcid()).get());
			
			m.addAttribute("allRoles", ConstantData.ROLES.getAllRoles());
			m.addAttribute("allQualifications", ConstantData.EDUCATION.getAllEducations());
			m.addAttribute("allProjects", ConstantData.PROJECTS.getAllProjects());
			m.addAttribute("allSkills", ConstantData.SKILLS.getAllSkills());
			m.addAttribute( "demands", allDemands);
			
			System.out.println("repository.findAll() : " + repository.findAll());
			m.addAttribute( "demand", new Demand());
			loginService.addLoggedUserDetails(user.getNcid());
			String userId = loginService.getLoggedUserDetails();
			String userAccess = loginService.getLoggedInUser(userId).getUserAccessLevel();
			System.out.println("userAccess ==>" + userAccess);
			m.addAttribute("userAccess", userAccess);
			return "internalprojectportaldashboard";
		}
		
		return "errorPage";
	}
	
	
	@RequestMapping(path = "/ncjobsearch/logout", method = RequestMethod.POST)
	public String logout(Model m) {
		m.addAttribute("userlogin", new UserLoginPageMapper());
		loginService.removeLoggedUserDetails() ;
		return "loginPage";
	}
	
	@ModelAttribute
	public void globalVariables(Model m) {

		String userId = loginService.getLoggedUserDetails();
		
		  if (userId != null) { String userAccess =
		  loginService.getLoggedInUser(userId).getUserAccessLevel();
		  System.out.println("userAccess ==>" + userAccess);
		  m.addAttribute("userAccess", userAccess); }
		 
	}

}
