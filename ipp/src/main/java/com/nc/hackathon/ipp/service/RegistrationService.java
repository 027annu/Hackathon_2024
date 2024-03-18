package com.nc.hackathon.ipp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.model.constant.ConstantData.ROLES;
import com.nc.hackathon.ipp.service.dao.RegistrationDaoService;
import com.nc.hackathon.ipp.service.dao.UserDaoService;


/**
 * This class will contain all required service related to User registration
 * @author sugh1218
 *
 */
@Service
public class RegistrationService {
	
	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private RegistrationDaoService registrationService;

	public boolean checkAndThenCreateUser(UserRegistration userRegistration, MultipartFile resume) throws IOException {
	
		
    	System.out.println("User Details :" + userRegistration);
    	if (resume != null && resume.getSize() > 0) {
    		System.out.println("User resume :" + resume.getOriginalFilename());
    		userRegistration.setResume(resume.getBytes());
    	}
    	
		User user = userDaoService.findByncid(userRegistration.getNcId());
		
		if (user != null) {
			System.out.println("User already exists : " + userRegistration.getFullName()); 
			return true;
		}
		
		System.out.println("Registering New User");
		
		//Save User
		String level = ROLES.getUserAccessLevel().get(userRegistration.getCurrentRole()); //Use Service to get Level from User Role
		System.out.println("Level Is : " + level);
		User newUSer = new User(userRegistration.getNcId(), userRegistration.getFullName(), userRegistration.getEMail(), userRegistration.getConfirmPassword(), level);
		userDaoService.save(newUSer);
		
		//Create User Profile and save resume
		saveUserResume(userRegistration, resume);
		registrationService.save(userRegistration);
		
		return false;
		
	}
	
	
	public boolean checkAndUpdateUser(UserRegistration userRegistration, MultipartFile resume) throws IOException {
	
		
    	System.out.println("User Details :" + userRegistration.getNcId());
    	if (resume != null && resume.getSize() > 0) {
    		System.out.println("User resume :" + resume.getOriginalFilename());
    		userRegistration.setResume(resume.getBytes());
    	}
    	
		User user = userDaoService.findByncid(userRegistration.getNcId());
		
		
		System.out.println("Updating User Details");
		
		//Save User
		//Update User Profile and save resume
		saveUserResume(userRegistration, resume);
		registrationService.save(userRegistration);
		
		return false;
		
	}
	
	private void saveUserResume(UserRegistration userRegistration, MultipartFile resume) throws IOException {
		System.out.println("resume content : " + resume.getContentType());
		System.out.println("resume size : " + resume.getSize());
		if (resume != null && resume.getSize() > 0) {
			String serverDirPath = new ClassPathResource("static/uploads/").getFile().getAbsolutePath();
					
			File resumePath = new File(serverDirPath + File.separator + userRegistration.getNcId());
			resumePath.mkdirs();
			
			FileOutputStream fos = new FileOutputStream(resumePath + File.separator + resume.getOriginalFilename());
			fos.write(resume.getBytes());
			fos.close();
			
			userRegistration.setResumePath(File.separator + "uploads" + File.separator + userRegistration.getNcId() + File.separator + resume.getOriginalFilename());
		}
	}
}
