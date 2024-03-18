package com.nc.hackathon.ipp.controller;

import com.nc.hackathon.ipp.model.Demand;
import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.model.constant.ConstantData;
import com.nc.hackathon.ipp.repository.DemandRepository;
import com.nc.hackathon.ipp.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class DemandController {
	
	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private com.nc.hackathon.ipp.service.dao.RegistrationDaoService userRegistrationService;

    @Autowired
    private DemandRepository repository;

    @GetMapping("/ncjobsearch/fetchJobs")
    public String fetchJobs(Model m) {
		String userId = loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(userId);
		
		List<Demand> allDemands = new ArrayList<>();
		for(Demand demand: repository.findAll()){
			System.out.println("Demand Status : "+demand.getStatus());
		   if(demand.getStatus().equals("Open")){
		      allDemands.add(demand);
		   }
		}
	
		if (userId != null) {
			m.addAttribute("userName", user.getName());
			m.addAttribute("userId", user.getNcid());
			m.addAttribute("userProfile", userRegistrationService.findById(userId).get());
			m.addAttribute("allRoles", ConstantData.ROLES.getAllRoles());
			m.addAttribute("allQualifications", ConstantData.EDUCATION.getAllEducations());
			m.addAttribute("allProjects", ConstantData.PROJECTS.getAllProjects());
			m.addAttribute("allSkills", ConstantData.SKILLS.getAllSkills());
			m.addAttribute( "demands", allDemands);
			System.out.println("Reloading Demand Page");

  		  if (userId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(userId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			  m.addAttribute("userAccess", userAccess); 
  		  }
			return "internalprojectportaldashboard";
		}
		return "errorPage";
	}
    

    @GetMapping("/ncjobsearch/createDemand")
    public ModelAndView createDemand(ModelAndView  skills) {
        skills.addObject("allSkills", ConstantData.SKILLS.getAllSkills());
        
		String userId = loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(userId);
	
		if (userId != null) {
			skills.addObject("userName", user.getName());
			skills.addObject("userProfile", userRegistrationService.findById(userId).get());
		}
		if (userId == null) {
			throw new NullPointerException();
		}
		
		  if (userId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(userId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			skills.addObject("userAccess", userAccess); 
  		  }
        skills.setViewName("CreateDemand");
        return skills;
    }

    @PostMapping("/saveJob")
    @ResponseBody
    public RedirectView createDemand(@ModelAttribute Demand demand) {
        demand.setCreationDate(new Date());
        demand.setStatus("Open");
        repository.save(demand);
        return new RedirectView("/ncjobsearch/myjoblists");
    }

   
    @GetMapping("/ncjobsearch/getJob/{id}")
    public ModelAndView getJob(@PathVariable("id") int demandId, Model model){
        Optional<Demand> job = repository.findById(demandId);   
		String userId = loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(userId);
		model.addAttribute("userName", user.getName());	
		
		  if (userId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(userId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			model.addAttribute("userAccess", userAccess); 
  		  }
        return new ModelAndView("demandDetails","demand",job.get());
    }
    
    @GetMapping("/ncjobsearch/myjoblists")
    public ModelAndView getMyJobList(ModelAndView mv){
    	String empId = loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(empId);
		
        mv.setViewName("myjoblist");
        mv.addObject("userName", user.getName());
        mv.addObject("demands",repository.getByCreatedBy(empId));
		  if (empId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(empId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			mv.addObject("userAccess", userAccess); 
  		  }
        return mv;
    }
 

}
