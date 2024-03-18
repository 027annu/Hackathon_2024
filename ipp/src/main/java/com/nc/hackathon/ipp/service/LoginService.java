package com.nc.hackathon.ipp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nc.hackathon.ipp.model.User;
import com.nc.hackathon.ipp.model.UserLoginPageMapper;
import com.nc.hackathon.ipp.model.UserRegistration;
import com.nc.hackathon.ipp.service.dao.RegistrationDaoService;
import com.nc.hackathon.ipp.service.dao.UserDaoService;

/**
 * Contains required methods for User LOgin Related service.
 * @author sugh1218
 *
 */
@Service
public class LoginService {
	
	static List<String> loggedInUser = new ArrayList<>();

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private RegistrationDaoService registrationService;
		
	public User validateLogin(UserLoginPageMapper inputUserData) {
		
		User user = userDaoService.findByncid(inputUserData.getNcidOrEmail());
		if (user == null) {
			user = userDaoService.findByeMail(inputUserData.getNcidOrEmail());
		}
		
		if (user != null && user.getPassword().equals(inputUserData.getPassword())) {
			return user;
		}
		return null;
	}
	
	
	public User getLoggedInUser(String userId) {
		User user = userDaoService.findByncid(userId);
		return user;
	}
	
	
	public static void addLoggedUserDetails(String userId) {
		loggedInUser.add(userId);
	}

	public static String getLoggedUserDetails() {
		String user = null;
		if (loggedInUser.size() > 0) {
			user =  loggedInUser.get(0);
		}
		return user;
	}
	
	public static String removeLoggedUserDetails() {
		return loggedInUser.remove(0);
	}
}
