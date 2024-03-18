package com.nc.hackathon.ipp.controller;

import com.nc.hackathon.ipp.model.Application;
import com.nc.hackathon.ipp.model.Demand;
import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.repository.ApplicationRepository;
import com.nc.hackathon.ipp.repository.DemandRepository;
import com.nc.hackathon.ipp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private DemandRepository demandRepository;
    
	@Autowired 
	private LoginService loginService;
	

    @PostMapping("/applyJob")
    public ModelAndView applyForJob(@ModelAttribute Application app, Model model) {
        System.out.println("Apply For Job");
        Optional<Demand> demand = demandRepository.findById(app.getDemandId());
        System.out.println("Emp Id: "+loginService.getLoggedUserDetails());
        List<Application> applicationList = applicationRepository.getByEmpId(loginService.getLoggedUserDetails());
        model.addAttribute("errorMessage", null);
        for (Application application : applicationList) {
            if (app.getDemandId() == application.getDemandId()) {
            System.out.println("Inside If");
            model.addAttribute("errorMessage", "Oops! It seems you've already applied for this job. You can review your application status in the 'Applied Jobs' tab or discover new opportunities.");
            model.addAttribute("demand",demand.get());
            
    		String userId = loginService.getLoggedUserDetails();   		
  		  if (userId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(userId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			  model.addAttribute("userAccess", userAccess); 
  		  }
  		  
            return new ModelAndView("demandDetails","demand",demand.get());
            }
        }
        System.out.println("Apply For Job Done");
      
        app.setAppliedDate(new Date());
        app.setEmpId(loginService.getLoggedUserDetails());
        demand.get().setNoOfApplicant(demand.get().getNoOfApplicant() + 1);
        demandRepository.save(demand.get());
        app.setStatus("Applied");
        applicationRepository.save(app);
        return new ModelAndView(new RedirectView("/ncjobsearch/app/"));
    }

    @GetMapping("/ncjobsearch/app")
    public ModelAndView getAppliedHistory(ModelAndView mv) {
    	String empId =loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(empId);
        System.out.println("empId" + user.getName());
        mv.setViewName("AppliedJobs");
        mv.addObject("userName", user.getName());
        mv.addObject("app", applicationRepository.getByEmpId(empId));
		  if (empId != null) { 
  			  String userAccess =
  					  loginService.getLoggedInUser(empId).getUserAccessLevel();
  			  System.out.println("userAccess ==>" + userAccess);
  			mv.addObject("userAccess", userAccess); 
  		  }
        return mv;
    }

    @GetMapping("/ncjobsearch/{demandId}")
    public ModelAndView getApplicationOnJob(@PathVariable("demandId") int demandId,ModelAndView mv) {
        System.out.println("Demand Id" + demandId);
      String empId =loginService.getLoggedUserDetails();
		
		User user = loginService.getLoggedInUser(empId);
        mv.setViewName("ApplicationPerJob");
        mv.addObject("userName", user.getName());
        mv.addObject("app", applicationRepository.getByDemandId(demandId));
        
        return mv;
    }

    @GetMapping("/application/{applicationId}/{status}")
    public RedirectView updateApplicationStatus(@PathVariable("applicationId") int applicationId, @PathVariable("status") String status) {
    	Optional<Application> application = applicationRepository.findById(applicationId);
    	if (status.equals("Accepted")) {
    	application.get().setStatus("Shortlisted");
    	applicationRepository.save(application.get());
    	} else if (status.equals("Rejected")) {
    	application.get().setStatus("Rejected");
    	applicationRepository.save(application.get());
    	} else if (status.equals("Selected")) {
    	application.get().setStatus("Selected");
    	applicationRepository.save(application.get());
    	}
    	
        return new RedirectView("/ncjobsearch/" + application.get().getDemandId());
    }
    
    @GetMapping("/cancelapplication/{demandId}/{status}")
    public ModelAndView updateApplicationStatusByJob(@PathVariable("demandId") int demandId, @PathVariable("status") String status) {
    List<Application> application = applicationRepository.getByDemandId(demandId);
    if (status.equals("Fulfilled")) {
    Demand demand = demandRepository.getById(demandId);
    demand.setStatus("Fulfilled");
    demandRepository.save(demand);
    for (Application appl : application) {
    if (appl.getStatus().equals("Applied") || appl.getStatus().equals("Shortlisted")) {
    appl.setStatus("Demand Closed");
    applicationRepository.save(appl);
    }
    }
    } else if (status.equals("Canceled")) {
    Demand demand = demandRepository.getById(demandId);
    demand.setStatus("Canceled");
    demandRepository.save(demand);
    for (Application app : application) {
    app.setStatus("Demand Closed");
    applicationRepository.save(app);
    }
    }
    return new ModelAndView("myjoblist", "demands", demandRepository.getByCreatedBy(loginService.getLoggedUserDetails()));
    }
}
