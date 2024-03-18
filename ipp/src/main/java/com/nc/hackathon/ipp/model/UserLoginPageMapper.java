package com.nc.hackathon.ipp.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * User Login Mapper : Will contain form input mapping and their mapping for user login page.
 * @author sugh1218
 *
 */
@Getter
@Setter
public class UserLoginPageMapper {

	@NotBlank(message = "Please enter your Username proceed.")
	private String ncidOrEmail;
	
	@NotBlank(message = "Please enter your Password proceed.")
	private String password;
	
	public UserLoginPageMapper() {
		
	}
}
