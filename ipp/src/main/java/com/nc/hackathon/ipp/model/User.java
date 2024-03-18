package com.nc.hackathon.ipp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * This class contains details for User Login Credentials, Status and Access Level.
 * @author sugh1218
 *
 */
@Getter
@Setter
@Entity
public class User {

	@Id
	@Column(name = "user_id")
	private String ncid;

	@Column(name = "user_name")
	private String name;

	@Column(name = "email_id")
	private String eMail;

	@Column(name = "user_password")
	private String password;

	@Column(name = "is_currently_loggedin")
	private String userStatus;

	@Column(name = "user_access_level")
	private String userAccessLevel;

	public User() {
			
		}

	public User(String ncid, String name, String eMail, String password, String userAccessLevel) {
			super();
			this.ncid = ncid;
			this.name = name;
			this.eMail = eMail;
			this.password = password;
			this.userAccessLevel = userAccessLevel;
		}
}
