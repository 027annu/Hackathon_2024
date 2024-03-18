package com.nc.hackathon.ipp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * UserRegistration Model : Will contain all attributes to be stored in DB and their respective validation for form inputs
 * @author sugh1218
 *
 */
@Getter
@Setter
@Entity
public class UserRegistration {
	
	@NotBlank(message = "NC Id cannot be blank.")
	@Size(min = 8, max = 8, message = "user id must be 8 characters long")
	//@Pattern(regexp = "$[A-Z]{4}[0-9]{4}^",message = "Incorrect format, please prvode actual valid NC ID.")
	@Id
	@Column(name = "nc_id")
	private String ncId;
	
	//================= Personal Info ====================
	@Size(min = 4, max = 40, message = "Name must be atleast 4 characters long")
	@Column(name = "full_name")
	private String fullName;
	
	//Need to check and fix pattern to validate eMail Id
	@NotBlank(message = "User Id cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9.]{6,30}@netcracker.com$", message="User id must be valid email id for domain netcracker.com")
	@Column(name = "eMail")
	private String eMail;
	
	@NotBlank(message = "Phone number must not be blank")
	@Size(min=10, max=10, message = "Phone number must be 10 digits long")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	//================= Professional Info =================	
//	@NotBlank(message = "Password cannot be blank.")
	@Transient
	private String password;

	//@NotBlank(message = "Confirm Password cannot be blank.")
	@Transient
	private String confirmPassword;
		
	@NotBlank(message = "Current Role Cannot be blank.")
	@Column(name = "current_role")
	private String currentRole;
	
	@Column(name = "current_project")
	private String currentProject;
	
	@Column(name = "Lwd_curr_project")
	private Date lwdCurrProject;
	
	@NotBlank(message = "Education Details cannot be blank.")
	@Column( name = "highest_education_details")
	private String highestEducationDetails;
	
	@Column(name = "work_experience")
	private double workExperience;
	
	@ElementCollection
	private List<String> skills;
	
	@Column(name = "manager_id")
	private String managerId;
	
	//================= Resume ==================
	@Lob
	@Column(name = "user_resume", columnDefinition = "longblob")
	private byte[] resume;
	
	//=============== Privacy and Consent =======
	//@AssertTrue(message = "ConcentCheckbox must be checked")
	private boolean consent;
	
	//@AssertTrue(message = "PrivacyPolicyCheckbox must be checked")
	private boolean privacyPolicy;
	
	
	@Column(name ="resume_path")
	private String resumePath;
	
	public UserRegistration() {
		
	}
	
	
	public Date getwdCurrProject() {
		return this.lwdCurrProject;
	}
	
	public void setLwdCurrProject(String lwdCurrProject) throws ParseException {
		
		if (lwdCurrProject != null) {
		SimpleDateFormat dof = new SimpleDateFormat("yyyy-MM-dd");
		
		this.lwdCurrProject = dof.parse(lwdCurrProject);
		}
	}
	
}
